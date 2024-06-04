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
        const content = document.querySelector(".content");
        const heading = document.createElement("h2");
        heading.textContent = "All Users";
        if (data.length > 0) {
            content.insertBefore(heading, content.firstChild);
        }
        userCardsContainer.innerHTML = "";
        data.forEach(user => {
          const card = document.createElement("div");
          card.classList.add("card");
          let cardColor;
          switch (user.type) {
            case "ADMIN":
              cardColor = "#428bca";
              break;
            case "EMPLOYEE":
              cardColor = "#5cb85c";
              break;
            case "MANAGER":
              cardColor = "#f0ad4e";
              break;
            default:
              cardColor = "#ccc";
          }

          card.style.backgroundColor = cardColor;
          const userInfoList = document.createElement("ul");
          userInfoList.classList.add("user-info");
          userInfoList.innerHTML = `
            <li>Email: ${user.email}</li>
            <li>Name: ${user.firstName} ${user.lastName}</li>
            <li>Type: ${user.type}</li>
          `;
  
          const updateButton = document.createElement("button");
          updateButton.textContent = "Update";
  
          updateButton.addEventListener("click", () => {
            console.log(`Update button clicked for user: ${user.id}`);
          });
  
          card.appendChild(userInfoList);
          card.appendChild(updateButton);
  
          userCardsContainer.appendChild(card);
        });
      })
      .catch(error => console.error(error));
  });
  
  const addProjectBtn = document.getElementById("addProjectBtn");
  addProjectBtn.addEventListener("click", function() {
    const content = document.querySelector(".content");
    content.innerHTML = `
      <h2>Add New Project</h2>
      <form id="projectForm">
        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" required>
        <br>
        <label for="projectDescription">Project Description:</label>
        <textarea id="projectDescription" name="projectDescription" required></textarea>
        <br>
        <button type="submit">Submit</button>
      </form>
    `;
  
    const projectForm = document.getElementById("projectForm");
    projectForm.addEventListener("submit", function(event) {
      event.preventDefault();
      const projectName = document.getElementById("projectName").value;
      const projectDescription = document.getElementById("projectDescription").value;
  
      fetch("http://localhost:8085/projects/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          projectName: projectName,
          projectDesc: projectDescription
        })
      })
      .then(response => response.text())
      .then(data => {
        alert(data);
        projectForm.reset();
      })
      .catch(error => console.error(error));
    });
  });