document.addEventListener("DOMContentLoaded", function() {
  const username = localStorage.getItem("name");
  const welcomeMessage = document.getElementById("welcomeMessage");
  welcomeMessage.innerText = username ? `Welcome, ${username}!` : "Welcome, Guest!";
});

document.getElementById('logoutBtn').addEventListener('click', function() {
  localStorage.removeItem("name");
  localStorage.removeItem("username");
  window.location.href = "login.html";
});

document.getElementById('viewManagerBtn').addEventListener('click', function() {
  const content = document.querySelector(".content");
  content.innerHTML = "";

  fetchDataAndDisplayTable('http://localhost:8085/managersproject', ['Manager Email', 'Project ID'], ['managerEmail', 'projectId']);
});

document.getElementById('viewEmployeeBtn').addEventListener('click', function() {
  const content = document.querySelector(".content");
  content.innerHTML = "";

  setupDropdownAndFetchData('http://localhost:8085/skills','http://localhost:8085/employeeskills', ['Employee Name', 'Project ID', 'Skill'], ['name', 'projectID', 'skillName']);
});

document.getElementById('viewProjectsBtn').addEventListener('click', function() {
  const content = document.querySelector(".content");
  content.innerHTML = "";

  fetchDataAndDisplayTable('http://localhost:8085/projects/all', ['Project ID', 'Project Name', 'Project Description'], ['projectId', 'projectName', 'projectDesc']);
});

function setupDropdownAndFetchData(dropdownUrl, dataUrl, headers, keys) {
  const dropdownContainer = document.createElement('div');
  dropdownContainer.classList.add('dropdownContainer');
  const content = document.querySelector(".content");
  content.appendChild(dropdownContainer);
  dropdownContainer.innerHTML = "";

  fetch(dropdownUrl)
    .then(response => response.json())
    .then(options => {
      const select = document.createElement('select');
      select.id = 'dataDropdown';
      const opt = document.createElement('option');
      opt.value = "all"; 
      opt.textContent = "All";
      select.appendChild(opt);
      options.forEach(option => {
        const opt = document.createElement('option');
        opt.value = option.skillName; // Assuming the options have a 'value' property
        opt.textContent = option.skillName; // Assuming the options have a 'text' property
        select.appendChild(opt);
      });

      dropdownContainer.appendChild(select);

      select.addEventListener('change', function() {
        const tableContainer = document.querySelector(".tableContainer");
        if (tableContainer) {
          tableContainer.innerHTML = "";
        }
        fetchDataAndDisplayTable(`${dataUrl}/${select.value}`, headers, keys, select.value === "all");
      });

      // Fetch initial data based on the first option
      fetchDataAndDisplayTable(`${dataUrl}/all`, headers, keys, true);
    })
    .catch(error => console.error('Error fetching dropdown options:', error));
}

function fetchDataAndDisplayTable(url, headers, keys, groupSkills = false) {
  const tableContainer = document.querySelector(".tableContainer") || document.createElement('div');
  tableContainer.classList.add('tableContainer');
  const content = document.querySelector(".content");
  if (!document.querySelector(".tableContainer")) {
    content.appendChild(tableContainer);
  }

  fetch(url)
    .then(response => response.json())
    .then(data => {
      if (groupSkills) {
        data = groupDataByEmployee(data);
      }
      displayTable(tableContainer, data, headers, keys);
    })
    .catch(error => console.error('Error fetching data:', error));
}

function groupDataByEmployee(data) {
  const groupedData = data.reduce((acc, item) => {
    const key = `${item.name}-${item.projectID}`;
    if (!acc[key]) {
      acc[key] = { ...item, skillName: [item.skillName] };
    } else {
      acc[key].skillName.push(item.skillName);
    }
    return acc;
  }, {});

  return Object.values(groupedData).map(item => ({
    ...item,
    skillName: item.skillName.join(', ')
  }));
}

function displayTable(container, data, headers, keys) {
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
      if ((key === 'projectId' || key === 'projectID') && item[key] == 0) {
        cell.textContent = 'Not assigned';
      } else {
        cell.textContent = item[key];
      }
      row.appendChild(cell);
    });
    tbody.appendChild(row);
  });
  table.appendChild(tbody);
  container.appendChild(table);
}
