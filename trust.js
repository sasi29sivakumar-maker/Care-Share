document.addEventListener("DOMContentLoaded", () => {
    const signupForm = document.querySelector("#trustSignupForm");
    const verifyButton = document.querySelector("#verify-button");
    const submitOtpButton = document.querySelector("#submit-otp");
    const closePopupButton = document.querySelector("#close-popup");
    const otpPopup = document.querySelector("#otp-popup");
    const signupSubmitButton = document.querySelector("#signup-submit");
    let contactNo = "";
    let isVerified = false;

    // Disable signup button initially
    signupSubmitButton.disabled = true;

    // 1️⃣ SEND OTP
    verifyButton.addEventListener("click", async () => {
        contactNo = document.querySelector("#contact")?.value.trim();

        if (!contactNo) {
            alert("Please enter a valid phone number.");
            return;
        }

        try {
            let response = await fetch("http://localhost:9090/api/otp/send", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ phoneNumber: contactNo })
            });

            if (response.ok) {
                alert("OTP sent to " + contactNo);
                otpPopup.style.display = "block";
            } else {
                alert("Failed to send OTP.");
            }
        } catch (error) {
            console.error("Error sending OTP:", error);
            alert("Server error.");
        }
    });

    // 2️⃣ VERIFY OTP
    submitOtpButton.addEventListener("click", async () => {
        let otpCode = document.querySelector("#otp-code")?.value.trim();

        if (!otpCode) {
            alert("Enter the OTP received.");
            return;
        }

        try {
            let response = await fetch("http://localhost:9090/api/otp/verify", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ phoneNumber: contactNo, otp: otpCode })
            });

            let result = await response.json();
            if (response.ok) {
                alert(result.message);
                isVerified = true;
                signupSubmitButton.disabled = false;
                otpPopup.style.display = "none";
                // ✅ Update Verify Button
                verifyButton.textContent = "Verified";
                verifyButton.disabled = true; // Disable button after verification
                verifyButton.style.backgroundColor = "green"; // Change button color
                verifyButton.style.cursor = "not-allowed"; // Change cursor to indicate no more clicks
            } else {
                alert("Invalid OTP. Try again.");
            }
        } catch (error) {
            console.error("Error verifying OTP:", error);
            alert("Server error.");
        }
    });

    // 3️⃣ CLOSE POPUP
    closePopupButton.addEventListener("click", () => {
        otpPopup.style.display = "none";
    });

    // 4️⃣ SIGNUP FUNCTION (Only if Phone is Verified)
    signupForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        if (!isVerified) {
            alert("Please verify your phone number before signing up.");
            return;
        }

        let trustName = document.querySelector("#trustName").value.trim();
        let trustId = document.querySelector("#trustId").value.trim();
        let email = document.querySelector("#trustEmail").value.trim();
        let password = document.querySelector("#trustPassword").value.trim();
        let licenseFile = document.querySelector("#trust-licence").files[0];

        if (!trustName || !trustId || !email || !password || !contactNo || !licenseFile) {
            alert("All fields are required!");
            return;
        }

        let formData = new FormData();
        formData.append("trustName", trustName);
        formData.append("trustId", trustId);
        formData.append("email", email);
        formData.append("password", password);
        formData.append("contactNo", contactNo);
        formData.append("licenseFile", licenseFile);

        try {
            const response = await fetch("http://localhost:9090/api/trusts/signup", {
                method: "POST",
                body: formData
            });

            if (response.ok) {
                alert("Trust Registered successful!");
                signupForm.reset();
                signupSubmitButton.disabled = true;
                isVerified = false;
                window.location.href = "user.html";
            } else {
                alert("Signup failed!");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Error connecting to server.");
        }
    });
});


document.getElementById("trustLoginForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    let loginData = new URLSearchParams();
    loginData.append("email", document.getElementById("loginEmail").value);
    loginData.append("password", document.getElementById("loginPassword").value);

    try {
        const response = await fetch("http://localhost:9090/api/trusts/login", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: loginData
        });

        const result = await response.json();

        if (response.ok) {
            // ✅ Store trust ID in session storage (for donor identification)
            sessionStorage.setItem("id", result.id);

            // ✅ Store authentication token in local storage (for future API requests)
            localStorage.setItem("authToken", result.token || "dummy-token");

            alert(result.message || "Login successful!");

            // ✅ Redirect to Trust_Page.html with id as a URL parameter
            window.location.href = `Trust_Page.html?id=${result.id}`;
        } else {
            alert(result.error || "Invalid email or password!");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Login failed!");
    }
});



