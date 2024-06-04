document.addEventListener("DOMContentLoaded", function() {
    var username = localStorage.getItem("name");
    if (username) {
        document.getElementById("welcomeMessage").innerText = "Welcome, " + username + "!";
    } else {
        document.getElementById("welcomeMessage").innerText = "Welcome, Guest!";
    }
});

const addBtn = document.getElementById("addUser");
addBtn.addEventListener("click", function() {
    window.location.href = "addEmployeeManager.html";
});

const viewUsersBtn = document.getElementById("viewUsersBtn");
const userCardsContainer = document.querySelector(".user-cards");

viewUsersBtn.addEventListener("click", function() {
  fetch("http://localhost:8085/all")
    .then(response => response.json())
    .then(data => {
      userCardsContainer.innerHTML = "";
      data.forEach(user => {
        const card = document.createElement("div");
        card.classList.add("card");
        card.innerHTML = `<h2>${user.email}</h2>`;
        card.innerHTML = ``;
        userCardsContainer.appendChild(card);
      });
    })
    .catch(error => console.error(error));
});

