// Функция для получения фамилии сотрудника и отображения на новой странице
function saveLastName() {
    const lastName = document.getElementById('employeeLastName').value;
    window.location.href = `newpage.html?lastName=${lastName}`;
}
function displayLastName() {
    const urlParams = new URLSearchParams(window.location.search);
    const lastName = urlParams.get('lastName');
    const displayField = document.getElementById('displayLastName');
    
    if (lastName) {
        displayField.value = lastName;
    }
}

// Вызываем функцию при загрузке страницы
window.onload = displayLastName;
function confirmDelete(event) {
    event.preventDefault();
    const row = event.target.closest('tr'); // Находим ближайшую строку
    const index = row.getAttribute('class').split(' ')[1]; // Получаем индекс строки
    const confirmation = confirm('Вы уверены, что хотите удалить этого сотрудника?');
    
    if (confirmation) {
        // Если пользователь подтвердил удаление, скрываем строку
        row.style.display = 'none';
    }
}

// Добавляем обработчик событий для всех гиперссылок "Удалить"
const deleteLinks = document.querySelectorAll('.delete-link');
deleteLinks.forEach(link => {
    link.addEventListener('click', confirmDelete);
});


function clearText() {
    var inputField = document.getElementById("employeeName");
    if (inputField.value == "например, Иванов") {
      inputField.value = "";
    }
  }

  function restoreText() {
    var inputField = document.getElementById("employeeName");
    if (inputField.value == "") {
      inputField.value = "например, Иванов";
    }
  }
  function checkInputForNumber() {
    // Получаем значение из поля ввода
    var inputValue = document.getElementById('employeeNumber').value;

    // Проверяем, является ли введенное значение числом /^\d+$/- диапозон значений
    if (/^\d+$/.test(inputValue)) {
        // Введенное значение - число, выполните нужные действия
        alert('Введенное значение является числом: ' + inputValue);
    } else {
        // Введенное значение не является числом, выводим сообщение
        alert('Пожалуйста, введите число.');
    }
    
    // Очищаем поле ввода для следующего ввода
    document.getElementById('employeeNumber').value = '';
}

// Функция для поиска сотрудника по номеру
function searchByNumber() {
    var employeeNumber = document.getElementById("employeeNumber").value;
    var table = document.querySelector("table tbody");
    var rows = table.querySelectorAll("tr");

    for (var i = 0; i < rows.length; i++) {
        var numberCell = rows[i].querySelector("td:nth-child(2)");
        var employeeId = numberCell.querySelector("a").textContent;

        if (employeeId === employeeNumber) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}

// Функция для поиска сотрудника по имени
function searchByName() {
    var employeeName = document.getElementById("employeeName").value.toLowerCase();
    var table = document.querySelector("table tbody");
    var rows = table.querySelectorAll("tr");

    for (var i = 0; i < rows.length; i++) {
        var nameCell = rows[i].querySelector("td:nth-child(3)");
        var employeeNameText = nameCell.textContent.toLowerCase();

        if (employeeNameText.includes(employeeName)) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}

// Функция для отображения всех сотрудников (сброс фильтров)
function showAllEmployees() {
    var table = document.querySelector("table tbody");
    var rows = table.querySelectorAll("tr");

    for (var i = 0; i < rows.length; i++) {
        rows[i].style.display = "";
    }
}



function removeClass(event) {
    event.target.closest('tr').remove();
}
function createPerson() {
  
    var tbody = document.querySelector('tbody');

    
    var newPersonRow = document.createElement('tr');

   

    var checkbox = document.createElement('input');

    checkbox.type = "checkbox";
    checkbox.name = "name";
    checkbox.value = "value";
    checkbox.id = "id";
    checkbox.style.margin = "14px";
    newPersonRow.appendChild(checkbox);

    var nameCell = document.createElement('td');
    nameCell.textContent = prompt('Введите номер пользователя:');
    nameCell.style.color = "red";
    newPersonRow.appendChild(nameCell);

    var ageCell = document.createElement('td');
    ageCell.textContent = prompt('Введите данные пользователя:');
    newPersonRow.appendChild(ageCell);

    var workCell = document.createElement('td');
    workCell.textContent = prompt('Должность');
    newPersonRow.appendChild(workCell);

    var dateCell = document.createElement('td');
    dateCell.textContent = prompt('Дата принятия');
    newPersonRow.appendChild(dateCell);

    var departCell = document.createElement('td');
    departCell.textContent = prompt('Департамент');
    newPersonRow.appendChild(departCell);
    

    var button = document.createElement('button');

    
    button.className = 'btn_delet 3';

   
    button.onclick = removeClass;

  
    button.textContent = 'Удалить';

    button.style.margin = "10px";

    newPersonRow.appendChild(button);
   
    tbody.appendChild(newPersonRow);
}