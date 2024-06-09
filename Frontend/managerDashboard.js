document.addEventListener("DOMContentLoaded", function() {
    const username = localStorage.getItem("name");
    const welcomeMessage = document.getElementById("welcomeMessage");
    welcomeMessage.innerText = username ? `Welcome, ${username}!` : "Welcome, Guest!";
  });
  
  document.getElementById('viewManagerBtn').addEventListener('click', function() {
    fetchDataAndDisplayTable('http://localhost:8085/managersproject', ['Manager Email', 'Project ID'], ['managerEmail', 'projectId']);
  });
  
  document.getElementById('viewEmployeeBtn').addEventListener('click', function() {
    fetchDataAndDisplayTable('http://localhost:8085/employeesproject', ['Employee Email', 'Project ID'], ['employeeEmail', 'projectId']);
  });
  
  document.getElementById('viewProjectsBtn').addEventListener('click', function() {
    fetchDataAndDisplayTable('http://localhost:8085/projects/all', ['Project ID', 'Project Name', 'Project Description'], ['projectId', 'projectName', 'projectDesc']);
  });
  
  function fetchDataAndDisplayTable(url, headers, keys) {
    const content = document.querySelector(".content");
    content.innerHTML = "";
  
    fetch(url)
      .then(response => response.json())
      .then(data => {
        displayTable(content, data, headers, keys);
      })
      .catch(error => console.error('Error fetching data:', error));
  }
  
  function displayTable(content, data, headers, keys) {
    const container = document.createElement('div');
    content.appendChild(container);
    container.innerHTML = '';
  
    if (data.length === 0) {
      alert("No data found.");
      return;
    }
  
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
  
    const tbody = document.createElement('tbody');
    data.forEach(item => {
      const row = document.createElement('tr');
      keys.forEach(key => {
        const cell = document.createElement('td');
        cell.textContent = item[key];
        row.appendChild(cell);
      });
      tbody.appendChild(row);
    });
    table.appendChild(tbody);
    container.appendChild(table);
  }
  