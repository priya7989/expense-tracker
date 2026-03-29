// 🔐 LOGIN FUNCTION
async function login() {
    let user = document.getElementById("username").value;
    let pass = document.getElementById("password").value;

    try {
        let response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `username=${user}&password=${pass}`
        });

        let result = await response.text();

        if (result === "SUCCESS") {
            // Store logged-in user
            localStorage.setItem("user", user);

            // Redirect to dashboard
            window.location.href = "index.html";
        } else {
            document.getElementById("error").innerText = "Invalid credentials";
        }

    } catch (error) {
        console.error("Error:", error);
        alert("Server not running or connection issue");
    }
}

// 🎯 DASHBOARD LOAD FUNCTION
window.onload = function () {
    let user = localStorage.getItem("user");

    // If user not logged in → redirect to login
    if (!user && window.location.pathname.includes("index.html")) {
        window.location.href = "login.html";
        return;
    }

    // Show welcome message (only if element exists)
    let welcome = document.getElementById("welcome");
    if (welcome) {
        welcome.innerText = "Welcome, " + user;
    }
};

// 🚪 LOGOUT FUNCTION
function logout() {
    localStorage.removeItem("user");
    window.location.href = "login.html";
}