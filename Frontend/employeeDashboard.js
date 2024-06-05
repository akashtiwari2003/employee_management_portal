document.addEventListener("DOMContentLoaded", function() {
    var username = localStorage.getItem("name");
    if (username) {
        document.getElementById("welcomeMessage").innerText = "Welcome, " + username + "!";
    } else {
        document.getElementById("welcomeMessage").innerText = "Welcome, Guest!";
    }
});

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('viewEmployeeBtn').addEventListener('click', fetchEmployeeData);
  });
  
  async function fetchEmployeeData() {
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
    const tableContainer = document.getElementById('employeeTableContainer');
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