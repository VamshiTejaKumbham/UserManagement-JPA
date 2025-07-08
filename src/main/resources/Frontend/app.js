
const API_URL = "http://localhost:8082/api/users";

document.addEventListener("DOMContentLoaded", () => {
    loadUsers();

    document.getElementById("add-user-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    if (!name || !email || !password) return;

    try {
        const res = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify([{ name, email, password }])
        });
        if (!res.ok) throw new Error("Failed to add user");
        showMessage("User added successfully!", "green");
        document.getElementById("add-user-form").reset();
        loadUsers();
    } catch (err) {
        showMessage("Error: " + err.message, "red");
    }
});
});

async function loadUsers() {
    try {
        const res = await fetch(API_URL);
        if (!res.ok) throw new Error("Failed to fetch users");
        const users = await res.json();
        renderUsers(users);
    } catch (err) {
        showMessage("Error: " + err.message, "red");
    }
}

function renderUsers(users) {
    const tbody = document.querySelector("#users-table tbody");
    tbody.innerHTML = "";
    users.forEach(user => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td><button onclick="deleteUser(${user.id})">Delete</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function deleteUser(id) {
    if (!confirm("Are you sure you want to delete this user?")) return;
    try {
        const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        if (res.status === 204) {
            showMessage("User deleted.", "green");
            loadUsers();
        } else if (res.status === 404) {
            showMessage("User not found.", "red");
        } else {
            throw new Error("Failed to delete user");
        }
    } catch (err) {
        showMessage("Error: " + err.message, "red");
    }
}

function showMessage(msg, color) {
    const div = document.getElementById("message");
    div.textContent = msg;
    div.style.color = color || "#e74c3c";
    setTimeout(() => { div.textContent = ""; }, 3000);
}


window.deleteUser = deleteUser;
