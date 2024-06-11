document.addEventListener("DOMContentLoaded", function() {
    var username = localStorage.getItem("name");
    if (username) {
        document.getElementById("welcomeMessage").innerText = "Welcome, " + username + "!";
    } else {
        document.getElementById("welcomeMessage").innerText = "Welcome, Guest!";
    }
});

document.getElementById('logoutBtn').addEventListener('click', function() {
  localStorage.removeItem("name");
  localStorage.removeItem("username");
  window.location.href = "login.html";
});

const addBtn = document.getElementById("addUser");
addBtn.addEventListener("click", function() {
    const content = document.querySelector(".content");
    content.innerHTML = "";
    window.location.href = "addEmployeeManager.html";
});

const viewUsersBtn = document.getElementById("viewUsersBtn");
viewUsersBtn.addEventListener("click", function() {
  const content = document.querySelector(".content");  
  content.innerHTML = "";
    displayFilterButtons();
    fetchUsers("http://localhost:8085/all");
});
  
  function displayFilterButtons() {
    const content = document.querySelector(".content");
    const filterButtonsContainer = document.createElement("div");
    filterButtonsContainer.classList.add("filter-buttons");
    
    const filterAllBtn = document.createElement("button");
    filterAllBtn.id = "filterAllBtn";
    filterAllBtn.textContent = "All";
    filterAllBtn.addEventListener("click", function() {
        fetchUsers("http://localhost:8085/all");
    });

    const filterManagersBtn = document.createElement("button");
    filterManagersBtn.id = "filterManagersBtn";
    filterManagersBtn.textContent = "Managers";
    filterManagersBtn.addEventListener("click", function() {
        fetchUsers("http://localhost:8085/manager");
    });

    const filterEmployeesBtn = document.createElement("button");
    filterEmployeesBtn.id = "filterEmployeesBtn";
    filterEmployeesBtn.textContent = "Employees";
    filterEmployeesBtn.addEventListener("click", function() {
        fetchUsers("http://localhost:8085/employee");
    });

    filterButtonsContainer.appendChild(filterAllBtn);
    filterButtonsContainer.appendChild(filterManagersBtn);
    filterButtonsContainer.appendChild(filterEmployeesBtn);

    content.appendChild(filterButtonsContainer);
  }

  function fetchUsers(url) {
    const content = document.querySelector(".content");
    const userCardsContainer = document.querySelector(".user-cards") || document.createElement("div");
    userCardsContainer.innerHTML = "";
    userCardsContainer.classList.add("user-cards");
    fetch(url)
      .then(response => response.json())
      .then(data => {
        let heading = content.querySelector("h2");
        if (!heading) {
            heading = document.createElement("h2");
            heading.textContent = "Users";
            content.insertBefore(heading, content.firstChild);
        }
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
            fetchUserDetails(user.email);
            console.log(`Update button clicked for user: ${user.email}`);
          });
  
          card.appendChild(userInfoList);
          card.appendChild(updateButton);
  
          userCardsContainer.appendChild(card);
        });
        content.appendChild(userCardsContainer);
      })
      .catch(error => console.error(error));
}

function fetchUserDetails(email) {
  fetch("http://localhost:8085/user/"+email)
      .then(response => response.json())
      .then(user => {
          showUpdateForm(user);
      })
      .catch(error => console.error(error));
}

function showUpdateForm(user) {
  const content = document.querySelector(".content");
  content.innerHTML = `
    <h2>Update User</h2>
    <form id="updateForm">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" value="${user.email}" readonly>
      <br>
      <label for="firstName">First Name:</label>
      <input type="text" id="firstName" name="firstName" value="${user.firstName}" required>
      <br>
      <label for="lastName">Last Name:</label>
      <input type="text" id="lastName" name="lastName" value="${user.lastName}" required>
      <br>
      <label for="password">Password:</label>
      <input type="password" id="password" name="password" value = "${user.password}"required>
      <br>
      <button type="submit">Submit</button>
    </form>
  `;

  const updateForm = document.getElementById("updateForm");
  updateForm.addEventListener("submit", function(event) {
      event.preventDefault();
      const email = document.getElementById("email").value;
      const firstName = document.getElementById("firstName").value;
      const lastName = document.getElementById("lastName").value;
      const password = document.getElementById("password").value;

      fetch(`http://localhost:8085/user/update`, {
          method: "PUT",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify({
              email: email,
              firstName: firstName,
              lastName: lastName,
              password: password,
          })
      })
      .then(response => response.text())
      .then(data => {
          alert(data);
          viewUsersBtn.click(); 
      })
      .catch(error => console.error(error));
  });
}

  const addProjectBtn = document.getElementById("addProjectBtn");
  addProjectBtn.addEventListener("click", function() {
    const content = document.querySelector(".content");
    content.innerHTML = "";
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

  const viewEmployeeBtn = document.getElementById("viewEmployeeBtn");
  viewEmployeeBtn.addEventListener("click", function() {
    const content = document.querySelector(".content");
    content.innerHTML = "";
      fetch("http://localhost:8085/employeesproject")
          .then(response => response.json())
          .then(data => {
              const content = document.querySelector(".content");
              content.innerHTML = `
                  <h2>All Employees and Assigned Projects</h2>
                  <table id="employeeTable">
                      <thead>
                          <tr>
                              <th>ID</th>
                              <th>Project ID</th>
                              <th>Action</th>
                          </tr>
                      </thead>
                      <tbody>
                      </tbody>
                  </table>
              `;
  
              const employeeTableBody = document.querySelector("#employeeTable tbody");
              data.forEach(employee => {
                  const row = document.createElement("tr");
  
                  row.innerHTML = `
                      <td>${employee.employeeEmail}</td>
                      <td>${employee.projectId == 0?"Not Assigned":employee.projectId}</td>
                  `;
  
                  const actionCell = document.createElement("td");
                  const actionButton = document.createElement("button");
                  if (employee.projectId === 0) {
                      actionButton.textContent = "Assign";
                      actionButton.style.backgroundColor = 'green';
                  } else {
                      actionButton.textContent = "Unassign";
                      actionButton.style.backgroundColor = 'red';
                  }
  
                  actionButton.addEventListener("click", () => {
                      if (employee.projectId === 0) {
                        showProjectDropdown(employee.employeeEmail);
                          console.log(`Assign button clicked for employee: ${employee.id}`);
                      } else {
                        unassignProject(employee.employeeEmail);
                          console.log(`Unassign button clicked for employee: ${employee.id}`);
                      }
                  });
  
                  actionCell.appendChild(actionButton);
                  row.appendChild(actionCell);
                  employeeTableBody.appendChild(row);
              });
          })
          .catch(error => console.error(error));
  });

  function showProjectDropdown(employeeEmail) {
    fetch("http://localhost:8085/projects/all")
      .then(response => response.json())
      .then(projects => {
        const content = document.querySelector(".content");
        const dropdownContainer = document.createElement("div");
        const projectDropdown = document.createElement("select");
        const assignButton = document.createElement("button");
  
        projectDropdown.id = "projectDropdown";
        projects.forEach(project => {
          const option = document.createElement("option");
          option.value = project.projectId;
          option.textContent = project.projectName;
          projectDropdown.appendChild(option);
        });
  
        assignButton.textContent = "Assign Project";
        assignButton.addEventListener("click", () => {
          const selectedProjectId = projectDropdown.value;
          assignProjectToEmployee(employeeEmail, selectedProjectId);
        });
  
        dropdownContainer.appendChild(projectDropdown);
        dropdownContainer.appendChild(assignButton);
        content.appendChild(dropdownContainer);
      })
      .catch(error => console.error(error));
  }
  
  function assignProjectToEmployee(employeeEmail, projectId) {
    fetch(`http://localhost:8085/employee/assignemployee`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        employeeEmail: employeeEmail,
        projectId: projectId
      })
    })
      .then(response => response.text())
      .then(data => {
        alert(data);
        viewEmployeeBtn.click();
      })
      .catch(error => console.error(error));
  }

  function unassignProject(employeeEmail) {
    fetch("http://localhost:8085/employee/unassign/"+employeeEmail, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        employeeEmail: employeeEmail
      })
    })
      .then(response => response.text())
      .then(data => {
        alert(data);
        viewEmployeeBtn.click(); // Refresh the employee view
      })
      .catch(error => console.error(error));
  }


  const viewManagerBtn = document.getElementById("viewManagerBtn");
  viewManagerBtn.addEventListener("click", function() {
    const content = document.querySelector(".content");
    content.innerHTML = "";
      fetch("http://localhost:8085/managersproject")
          .then(response => response.json())
          .then(data => {
              const content = document.querySelector(".content");
              content.innerHTML = `
                  <h2>All Managers and Assigned Projects</h2>
                  <table id="managerTable">
                      <thead>
                          <tr>
                              <th>ID</th>
                              <th>Project ID</th>
                              <th>Action</th>
                          </tr>
                      </thead>
                      <tbody>
                      </tbody>
                  </table>
              `;
  
              const managerTableBody = document.querySelector("#managerTable tbody");
              data.forEach(manager => {
                  const row = document.createElement("tr");
  
                  row.innerHTML = `
                      <td>${manager.managerEmail}</td>
                      <td>${manager.projectId == 0?"Not Assigned":manager.projectId}</td>
                  `;
  
                  const actionCell = document.createElement("td");
                  const actionButton = document.createElement("button");
                  if (manager.projectId === 0) {
                      actionButton.textContent = "Assign";
                      actionButton.style.backgroundColor = 'green';
                  } else {
                      actionButton.textContent = "Unassign";
                      actionButton.style.backgroundColor = 'red';
                  }
  
                  actionButton.addEventListener("click", () => {
                      if (manager.projectId === 0) {
                        showManagerProjectDropdown(manager.managerEmail);
                          console.log(`Assign button clicked for manager: ${manager.projectId}`);
                      } else {
                        unassignProject(manager.managerEmail);
                          console.log(`Unassign button clicked for employee: ${manager.projectId}`);
                      }
                  });
  
                  actionCell.appendChild(actionButton);
                  row.appendChild(actionCell);
                  managerTableBody.appendChild(row);
              });
          })
          .catch(error => console.error(error));
  });

  function showManagerProjectDropdown(managerEmail) {
    fetch("http://localhost:8085/assignmanagerprojects")
      .then(response => response.json())
      .then(projects => {
        const content = document.querySelector(".content");
        const dropdownContainer = document.createElement("div");
        const projectDropdown = document.createElement("select");
        const assignButton = document.createElement("button");
  
        projectDropdown.id = "projectDropdown";
        projects.forEach(project => {
          const option = document.createElement("option");
          option.value = project.projectId;
          option.textContent = project.projectName;
          projectDropdown.appendChild(option);
        });
  
        assignButton.textContent = "Assign Project";
        assignButton.addEventListener("click", () => {
          const selectedProjectId = projectDropdown.value;
          assignProjectToManager(managerEmail, selectedProjectId);
        });
  
        dropdownContainer.appendChild(projectDropdown);
        dropdownContainer.appendChild(assignButton);
        content.appendChild(dropdownContainer);
      })
      .catch(error => console.error(error));
  }

  function assignProjectToManager(managerEmail, projectId) {
    fetch(`http://localhost:8085/assignmanager`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        managerEmail: managerEmail,
        projectId: projectId
      })
    })
      .then(response => response.text())
      .then(data => {
        alert(data);
        viewManagerBtn.click();
      })
      .catch(error => console.error(error));
  }

  const viewProjectsBtn = document.getElementById("viewProjectsBtn");
viewProjectsBtn.addEventListener("click", function() {
    const content = document.querySelector(".content");
    content.innerHTML = "";
    fetch("http://localhost:8085/projects/all")
        .then(response => response.json())
        .then(data => {
            content.innerHTML = `
                <h2>All Projects</h2>
                <table id="projectsTable">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            `;

            const projectsTableBody = document.querySelector("#projectsTable tbody");

            data.forEach(project => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${project.projectId}</td>
                    <td>${project.projectName}</td>
                    <td>${project.projectDesc}</td>
                    <td><button class="removeProjectBtn" data-id="${project.projectId}" style="background-color:red">Remove</button></td>
                `;

                projectsTableBody.appendChild(row);
            });

            const removeProjectBtns = document.querySelectorAll(".removeProjectBtn");
            removeProjectBtns.forEach(button => {
                button.addEventListener("click", function() {
                    const projectId = this.getAttribute("data-id");
                    fetch(`http://localhost:8085/projects/remove/${projectId}`, {
                        method: "DELETE"
                    })
                    .then(response => {
                        if (response.ok) {
                            this.closest("tr").remove();
                        } else {
                            console.error("Failed to delete the project.");
                        }
                    })
                    .catch(error => console.error("Error:", error));
                });
            });
        })
        .catch(error => console.error(error));
});
