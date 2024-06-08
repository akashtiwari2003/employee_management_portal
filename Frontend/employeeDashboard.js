document.addEventListener("DOMContentLoaded", function() {
    var username = localStorage.getItem("name");
    if (username) {
        document.getElementById("welcomeMessage").innerText = "Welcome, " + username + "!";
    } else {
        document.getElementById("welcomeMessage").innerText = "Welcome, Guest!";
    }
});

var username = localStorage.getItem("username");

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('viewEmployeeBtn').addEventListener('click', fetchEmployeeData);
    document.getElementById('viewInfoBtn').addEventListener('click', fetchProjectData);
    document.getElementById('updateSkills').addEventListener('click', fetchSkillsData);
  });
  
  async function fetchEmployeeData() {
    const content = document.querySelector(".content");
    content.innerHTML = "";
    try {
      const response = await fetch('http://localhost:8085/employeeskills'); // Replace with your API endpoint
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const employees = await response.json();
      const processedData = processEmployeeData(employees);
      displayEmployeeTable(processedData);
    } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
    }
  }
  
  function processEmployeeData(employees) {
    const employeeMap = {};
  
    employees.forEach(employee => {
      const { firstName, skillName } = employee;
      if (!employeeMap[firstName]) {
        employeeMap[firstName] = [];
      }
      employeeMap[firstName].push(skillName);
    });
  
    const processedData = [];
    for (const [firstName, skills] of Object.entries(employeeMap)) {
      processedData.push({ firstName, skills: skills.join(', ') });
    }
    return processedData;
  }
  
  function displayEmployeeTable(employees) {
    const content = document.querySelector(".content");
    const tableContainer = document.createElement("div");
    tableContainer.id = "employeeTableContainer";
    content.appendChild(tableContainer);
    tableContainer.innerHTML = '';
  
    const table = document.createElement('table');
    table.classList.add('employee-table');
  
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
  
    const headers = ['Name', 'Skills'];
    headers.forEach(headerText => {
      const th = document.createElement('th');
      th.textContent = headerText;
      headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);
  
    const tbody = document.createElement('tbody');
    employees.forEach(employee => {
      const row = document.createElement('tr');
      
      const firstNameCell = document.createElement('td');
      firstNameCell.textContent = employee.firstName;
      row.appendChild(firstNameCell);
      
      const skillsCell = document.createElement('td');
      skillsCell.textContent = employee.skills;
      row.appendChild(skillsCell);
  
      tbody.appendChild(row);
    });
    table.appendChild(tbody);
  
    tableContainer.appendChild(table);
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
                  managerTableBody.appendChild(row);
              });
          })
          .catch(error => console.error(error));
  });

  async function fetchProjectData() {
    const content = document.querySelector(".content");
    content.innerHTML = "";
    try {
        const response = await fetch("http://localhost:8085/employee/employeeproject/"+username);
        if(response.ok && response.body != null){
          const data = await response.json();
          displayProjectTable(data);
        }
    } catch (error) {
        console.log('Project not Assigned', null);
        alert("Project not Assigned");
    }
}

function displayProjectTable(data) {
    const content = document.querySelector(".content");
    content.innerHTML = `
        <h2>My Projects</h2>
        <table id="projectTable">
            <thead>
                <tr>
                    <th>Employee Email</th>
                    <th>Project ID</th>
                    <th>Manager Email</th>
                    <th>Project Name</th>
                    <th>Project Description</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    `;

    const projectTableBody = document.querySelector("#projectTable tbody");
  
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${data.employeeEmail}</td>
            <td>${data.projectId}</td>
            <td>${data.managerEmail}</td>
            <td>${data.projectName}</td>
            <td>${data.projectDesc}</td>
        `;
        projectTableBody.appendChild(row);
    } ;

    async function fetchSkillsData() {
      const content = document.querySelector(".content");
      content.innerHTML = "";
      try {
          const response = await fetch("http://localhost:8085/employeeandskills/" + username);
          if (!response.ok) {
              throw new Error('Network response was not ok');
          }
          const data = await response.json();
          displaySkillsTable(data);
      } catch (error) {
          console.error('There was a problem with the fetch operation:', error);
      }
  }
  
  function displaySkillsTable(data) {
      const content = document.querySelector(".content");
      content.innerHTML = `
          <h2>Update Skills</h2>
          <table id="skillsTable">
              <thead>
                  <tr>
                      <th>Name</th>
                      <th>Skills</th>
                      <th>Actions</th>
                  </tr>
              </thead>
              <tbody>
              </tbody>
          </table>
      `;
  
      const skillsTableBody = document.querySelector("#skillsTable tbody");
      const employeeMap = processEmployeeData(data);
  
      employeeMap.forEach(employee => {
          const row = document.createElement("tr");
  
          const firstNameCell = document.createElement("td");
          firstNameCell.textContent = employee.firstName;
          row.appendChild(firstNameCell);
  
          const skillsCell = document.createElement("td");
          skillsCell.textContent = employee.skills;
          row.appendChild(skillsCell);
  
          const actionsCell = document.createElement("td");
          const updateButton = document.createElement("button");
          updateButton.textContent = "Update";
          updateButton.addEventListener("click", () => fetchAvailableSkills(data));
          actionsCell.appendChild(updateButton);
          row.appendChild(actionsCell);
          skillsTableBody.appendChild(row);
      });
  }

  async function fetchAvailableSkills(data) {
    try {
        const response = await fetch('http://localhost:8085/skills');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const skills = await response.json();
        displayAvailableSkills(skills,data);
    } catch (error) {
        alert("An unexpected error occured");
        console.error('There was a problem with the fetch operation:', error);
    }
}

  function displayAvailableSkills(skills,data) {
    const content = document.querySelector(".content");
    content.innerHTML = `
        <h2>Available Skills</h2>
        <table id="availableSkillsTable">
            <thead>
                <tr>
                    <th>Skill ID</th>
                    <th>Skill Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    `;

    const availableSkillsTableBody = document.querySelector("#availableSkillsTable tbody");

    skills.forEach(skill => {
      console.log(skill.id);
      console.log(skill.skillName);
        const row = document.createElement("tr");

        const skillIdCell = document.createElement("td");
        skillIdCell.textContent = skill.id;
        row.appendChild(skillIdCell);

        const skillNameCell = document.createElement("td");
        skillNameCell.textContent = skill.skillName;
        row.appendChild(skillNameCell);

        const actionButton = document.createElement("button");
        const isSkillInUserSkills = data.some(empSkill => empSkill.skillName === skill.skillName);

        actionButton.textContent = isSkillInUserSkills ? "Remove" : "Add";
        actionButton.addEventListener("click", async function() {
          if(actionButton.textContent == "Add"){
            try {
              const response = await fetch('http://localhost:8085/addskill', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    employeeEmail: localStorage.getItem('username'),
                    skillName: skill.skillName
                })
              });
                if (!response.ok) {
                  throw new Error('Network response was not ok');
                }
                fetchSkillsData(); 
            } catch (error) {
              console.error('There was a problem with the fetch operation:', error);
            }
          }
          else{
            try {
              const response = await fetch('http://localhost:8085/removeskill', {
                  method: 'DELETE',
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  body: JSON.stringify({
                      employeeEmail: localStorage.getItem('username'),
                      skillId: skill.id
                  })
              });
              if (!response.ok) {
                  throw new Error('Network response was not ok');
              }
              fetchSkillsData();
          } catch (error) {
              console.error('There was a problem with the fetch operation:', error);
          }
          }
        });
        row.appendChild(actionButton);
        availableSkillsTableBody.appendChild(row);
    });
}
  
 