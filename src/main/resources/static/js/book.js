addBooksBtnEvent();
eventForBookPage();


function addBooksBtnEvent() {
    document.getElementById('addBtnBook').addEventListener('click', function (event) {
        event.preventDefault();
        let href = this.getAttribute('href');
        fetch(href).then(response => response.text()).then(fragment => {
            document.querySelector("#addModalBook").innerHTML = fragment;
        }).then(() => {
            let model = new bootstrap.Modal(document.getElementById('addModalBook'), {});
            model.show();
            document.getElementById("add_book").addEventListener('submit', event => submitNewBookForm(event))
        });
    });
}

function editBook(href) {

    fetch(href).then(response => response.text()).then(fragment => {
        document.querySelector("#editModalBook").innerHTML = fragment;
    }).then(() => {
        let model = new bootstrap.Modal(document.getElementById('editModalBook'), {});
        model.show();
        document.getElementById("edit_book").addEventListener('submit', event => submitEditBookForm(event))
    });
}


function eventForBookPage() {
    document.querySelectorAll('.table tbody tr').forEach(editTr =>
        editTr.addEventListener('dblclick', function (event) {
            event.preventDefault()
            let href = editTr.querySelector("a").getAttribute('href');
            editBook(href)

        }))
    document.querySelectorAll('.table .editBtn').forEach(editBtn =>
        editBtn.addEventListener('click', function (event) {
            event.preventDefault()
            let href = this.getAttribute('href');
            editBook(href)
        }))

    document.querySelectorAll('.table .deleteBtn').forEach(deleteBtn =>
        deleteBtn.addEventListener('click', function (event) {
            event.preventDefault()
            let href = this.getAttribute('href');
            document.querySelector('#deleteModal .modal-footer a').setAttribute('href', href)
            let model = new bootstrap.Modal(document.getElementById('deleteModal'), {});
            model.show();
            document.getElementById('delBook').addEventListener('click', function (event) {
                event.preventDefault()
                fetch(href).then(response => response.text()).then(fragment => {
                    document.querySelector(".book_list").innerHTML = fragment;
                    model.hide()
                    eventForBookPage();
                })
            })
        }))
}

async function submitNewBookForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = await fetch(request);
    let bookTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('addModalBook'))
    modal.hide();
    document.querySelector(".book_list").innerHTML = bookTable;

    eventForBookPage();
}

async function saveBook(request) {
    let response = await fetch(request);
    let bookTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('addModalBook'))
    modal.hide();
    document.querySelector(".book_list").innerHTML = bookTable;
    eventForBookPage()
}

async function dblclick11(event) {
    event.preventDefault();

}

async function submitEditBookForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = await fetch(request);
    let bookTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('editModalBook'))
    modal.hide();
    document.querySelector(".book_list").innerHTML = bookTable;

    eventForBookPage()

}


/*function tableSearch() { // поиск по реальному времени
    let phrase = document.getElementById('search-text');
    let table = document.getElementById('info-table');
    let regPhrase = new RegExp(phrase.value, 'i');
    let flag = false;
    for (let i = 1; i < table.rows.length; i++) {
        flag = false;
        for (let j = table.rows[i].cells.length - 1; j >= 0; j--) {
            flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
            if (flag) break;
        }
        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }

    }
}*/


async function searchBook() {
    let searchWord = document.getElementById('findBook').value
    const param = new URLSearchParams({
        "bookSearch": searchWord
    })
    fetch("references/books/filterBook?" + param).then(response => response.text()).then(fragment => {
        document.querySelector(".book_list").innerHTML = fragment
        eventForBookPage();
    })


}

