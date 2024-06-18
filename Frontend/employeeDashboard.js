document.addEventListener("DOMContentLoaded", function() {
  var username = localStorage.getItem("name") || "Guest";
  document.getElementById("welcomeMessage").innerText = `Welcome, ${username}!`;

  document.getElementById('viewEmployeeBtn').addEventListener('click', fetchEmployeeData);
  document.getElementById('viewInfoBtn').addEventListener('click', fetchProjectData);
  document.getElementById('updateSkills').addEventListener('click', fetchSkillsData);
  document.getElementById("viewManagerBtn").addEventListener('click', fetchManagerData);
});

document.getElementById('logoutBtn').addEventListener('click', function() {
  localStorage.removeItem("name");
  localStorage.removeItem("username");
  window.location.href = "login.html";
});

async function fetchEmployeeData() {
  clearContent();
  try {
      const response = await fetch('http://localhost:8085/employeeskills');
      if (!response.ok) throw new Error('Network response was not ok');
      
      const employees = await response.json();
      const processedData = processEmployeeData(employees);
      displayEmployeeTable(processedData);
  } catch (error) {
      handleError(error);
  }
}

function processEmployeeData(employees) {
  const employeeMap = employees.reduce((map, { firstName, skillName }) => {
      if (!map[firstName]) map[firstName] = [];
      map[firstName].push(skillName);
      return map;
  }, {});

  return Object.entries(employeeMap).map(([firstName, skills]) => ({
      firstName,
      skills: skills.join(', ')
  }));
}

function displayEmployeeTable(employees) {
  const content = document.querySelector(".content");
  const table = createTable(['Name', 'Skills']);

  employees.forEach(({ firstName, skills }) => {
      const row = table.insertRow();
      row.insertCell().textContent = firstName;
      row.insertCell().textContent = skills;
  });

  content.appendChild(table);
}

async function fetchManagerData() {
  clearContent();
  try {
      const response = await fetch("http://localhost:8085/managersproject");
      if (!response.ok) throw new Error('Network response was not ok');

      const data = await response.json();
      displayManagerTable(data);
  } catch (error) {
      handleError(error);
  }
}

function displayManagerTable(managers) {
  const content = document.querySelector(".content");
  const table = createTable(['ID', 'Project ID']);
  managers.forEach(({ managerEmail, projectId }) => {
      const row = table.insertRow();
      row.insertCell().textContent = managerEmail;
      row.insertCell().textContent = projectId == 0 ? "Not Assigned" : projectId;
  });

  content.appendChild(table);
}

async function fetchProjectData() {
  clearContent();
  try {
      const response = await fetch(`http://localhost:8085/employee/employeeproject/${localStorage.getItem("username")}`);
      if (!response.ok) {
        alert("An error occured");
      }     
      const texts = response.text();
      if((await texts).length != 0){
        const data = await response.json();
        displayProjectTable(data);
      } 
      else{
        alert("Project not asssigned");
      }
      
  } catch (error) {
      handleError(error, "An error occured");
  }
}

function displayProjectTable(data) {
  const content = document.querySelector(".content");
  const table = createTable(['Employee Email', 'Project ID', 'Manager Email', 'Project Name', 'Project Description']);

  const row = table.insertRow();
  row.insertCell().textContent = data.employeeEmail;
  row.insertCell().textContent = data.projectId;
  row.insertCell().textContent = data.managerEmail;
  row.insertCell().textContent = data.projectName;
  row.insertCell().textContent = data.projectDesc;
  content.appendChild(table);
}

async function fetchSkillsData() {
  clearContent();
  try {
      const response = await fetch(`http://localhost:8085/employeeandskills/${localStorage.getItem("username")}`);
      if (!response.ok) throw new Error('Network response was not ok');
      const data = await response.json();
      displaySkillsTable(data);
  } catch (error) {
      handleError(error);
  }
}

function displaySkillsTable(data) {
  const content = document.querySelector(".content");
  const table = createTable(['Name', 'Skills', 'Actions']);
  const employeeMap = processEmployeeData(data);
  if(data == ""){
    const updateButton = document.createElement("button");
      updateButton.textContent = "Update";
      updateButton.addEventListener("click", () => fetchAvailableSkills(data));
      content.appendChild(updateButton);
  }

  employeeMap.forEach(({ firstName, skills }) => {
      const row = table.insertRow();
      row.insertCell().textContent = firstName;
      row.insertCell().textContent = skills;

      const actionsCell = row.insertCell();
      const updateButton = document.createElement("button");
      updateButton.textContent = "Update";
      updateButton.addEventListener("click", () => fetchAvailableSkills(data));
      actionsCell.appendChild(updateButton);
  });

  content.appendChild(table);
}

async function fetchAvailableSkills(employeeSkills) {
  clearContent();
  try {
      const response = await fetch('http://localhost:8085/skills');
      if (!response.ok) throw new Error('Network response was not ok');

      const skills = await response.json();
      displayAvailableSkills(skills, employeeSkills);
  } catch (error) {
      handleError(error, "An unexpected error occurred");
  }
}

function displayAvailableSkills(skills, employeeSkills) {
  const content = document.querySelector(".content");
  const table = createTable(['Skill ID', 'Skill Name', 'Actions']);

  skills.forEach(skill => {
      const row = table.insertRow();
      row.insertCell().textContent = skill.id;
      row.insertCell().textContent = skill.skillName;

      const actionButton = document.createElement("button");
      const isSkillInUserSkills = employeeSkills.some(empSkill => empSkill.skillName === skill.skillName);
      actionButton.textContent = isSkillInUserSkills ? "Remove" : "Add";
      actionButton.addEventListener("click", () => handleSkillAction(skill, isSkillInUserSkills));
      row.appendChild(actionButton);
  });

  content.appendChild(table);
}

async function handleSkillAction(skill, isAdd) {
  const url = isAdd ? 'http://localhost:8085/removeskill' : 'http://localhost:8085/addskill';
  const method = isAdd ? 'DELETE' : 'POST';
  const body = JSON.stringify({
      employeeEmail: localStorage.getItem('username'),
      skillName: skill.skillName,
      ...(isAdd && { skillId: skill.id })
  });

  try {
      const response = await fetch(url, { method, headers: { 'Content-Type': 'application/json' }, body });
      if (!response.ok) throw new Error('Network response was not ok');
      fetchSkillsData();
  } catch (error) {
      handleError(error);
  }
}

function clearContent() {
  document.querySelector(".content").innerHTML = "";
}

function createTable(headers) {
  const table = document.createElement('table');
  table.classList.add('data-table');

  const thead = document.createElement('thead');
  const headerRow = document.createElement('tr');
  headers.forEach(headerText => {
      const th = document.createElement('th');
      th.textContent = headerText;
      headerRow.appendChild(th);
  });
  thead.appendChild(headerRow);
  table.appendChild(thead);

  table.appendChild(document.createElement('tbody'));
  return table;
}

function handleError(error, customMessage) {
  alert("An error occured while fetching data.")
  console.error('There was a problem with the fetch operation:', error);
  if (customMessage) alert(customMessage);
}
