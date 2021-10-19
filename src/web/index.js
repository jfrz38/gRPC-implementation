const buttons = ['nodeServer', 'csharpServer', 'pythonServer', 'javaServer']

function initializeNodeServer() {
    initializeServer("nodeServer")
}

function initializeCsharpServer() {
    initializeServer("csharpServer")
}

function initializePythonServer() {
    initializeServer("pythonServer")
}

function initializeJavaServer() {
    initializeServer("javaServer")
}

function openPage(pageName, element) {
    var color = "#EDF1FF"
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].style.color =
            tablinks[i].innerHTML.trim().toLowerCase() === pageName.toLowerCase() ? "black" : "white"
        tablinks[i].style.backgroundColor = "";
    }
    document.getElementById(pageName).style.display = "block";
    element.style.backgroundColor = color;
}

function initializeServer(id) {
    sessionStorage.setItem(id,true)
    buttons.forEach(button => {
        document.querySelectorAll('#' + button).forEach(button => {
            button.disabled = true
        })
    })
    
    document.querySelectorAll('#' + id + 'Div').forEach(div => {
        div.innerHTML += '<a class="inline server-start">Server started ✓</a>'
    })
}
function initalizeButtons() {
    
    buttons.forEach(name => {
        document.querySelectorAll('#' + name).forEach(button => {
            button.disabled = sessionStorage.getItem(button) ? true : false
        })

    })
}
initalizeButtons()
