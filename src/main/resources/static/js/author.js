
    addAuthorBtnEvent();
    eventForAuthorPage();


function addAuthorBtnEvent() {
    document.getElementById('addBtnAuthor').addEventListener('click', function (event) {
        event.preventDefault();
        let href = this.getAttribute('href');
        fetch(href).then(response => response.text()).then(fragment => {
            document.querySelector("#addModalAuthor").innerHTML = fragment;
        }).then(() => {
            let model = new bootstrap.Modal(document.getElementById('addModalAuthor'), {});
            model.show();
            document.getElementById("add_author").addEventListener('submit', event => submitNewAuthorForm(event))
        });
    });
}

function editAuthor(href) {

    fetch(href).then(response => response.text()).then(fragment => {
        document.querySelector("#editModalAuthor").innerHTML = fragment;
    }).then(() => {
        let model = new bootstrap.Modal(document.getElementById('editModalAuthor'), {});
        model.show();
        document.getElementById("edit_author").addEventListener('submit', event => submitEditAuthorForm(event))
    });
}


function eventForAuthorPage() {
    document.querySelectorAll('.table tbody tr').forEach(editTr =>
        editTr.addEventListener('dblclick', function (event) {
            event.preventDefault()
            let href = editTr.querySelector("a").getAttribute('href');
            editAuthor(href)

        }))
    document.querySelectorAll('.table .editBtn').forEach(editBtn =>
        editBtn.addEventListener('click', function (event) {
            event.preventDefault()
            let href = this.getAttribute('href');
            editAuthor(href)
        }))

    document.querySelectorAll('.table .deleteBtn').forEach(deleteBtn =>
        deleteBtn.addEventListener('click', function (event) {
            event.preventDefault()
            let href = this.getAttribute('href');
            document.querySelector('#deleteModalLabel .modal-footer a').setAttribute('href', href)
            let model = new bootstrap.Modal(document.getElementById('deleteModalLabel'), {});
            model.show();
            document.getElementById('delAuthor').addEventListener('click', function (event) {
                event.preventDefault()
                fetch(href).then(response => response.text()).then(fragment => {
                    document.querySelector(".author_list").innerHTML = fragment;
                    model.hide()
                    eventForAuthorPage();
                })
            })
        }))
}

async function submitNewAuthorForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = await fetch(request);
    let authorTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('addModalAuthor'))
    modal.hide();
    document.querySelector(".author_list").innerHTML = authorTable;

    eventForAuthorPage();
}

async function saveAuthor(request) {
    let response = await fetch(request);
    let authorTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('addModalAuthor'))
    modal.hide();
    document.querySelector(".author_list").innerHTML = authorTable;
    eventForAuthorPage()
}


async function dblclick12(event) {
    event.preventDefault();

}

async function submitEditAuthorForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = await fetch(request);
    let authorTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('editModal'))
    modal.hide();
    document.querySelector(".author_list").innerHTML = authorTable;

    eventForAuthorPage()

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

async function filterAuthor() {
    let searchWord = document.getElementById('findAuthor').value
    const param = new URLSearchParams({
        "str": searchWord
    })
    fetch( "references/authors/filterAuthor?" + param).then(response => response.text()).then(fragment => {
        document.querySelector(".author_list").innerHTML = fragment
        eventForAuthorPage();
    })


}

