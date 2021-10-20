const buttons = ['nodeServer', 'csharpServer', 'pythonServer', 'javaServer']
const gRPCport = 50051
const urls = {nodeServer: '/node', csharpServer: '/csharp', pythonServer: '/python', javaServer: '/java'}

document.addEventListener("initialize-buttons", this);
function handleEvent(event) {
    if(event.type === 'initialize-buttons'){
        initalizeButtons();
    }
}

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
    sessionStorage.setItem("server",id)
    buttons.forEach(button => {
        document.querySelectorAll('#' + button).forEach(button => {
            button.disabled = true
        })
    })
    
    document.querySelectorAll('#' + id + 'Div').forEach(div => {
        if(!div.innerHTML.includes("Server started ✓"))
            div.innerHTML += '<a class="inline server-start">Server started ✓</a>'
    })
}
function initalizeButtons() {
    console.log("ENTRA INITIIALIZE: ",sessionStorage.getItem("server"))
    const serverStarted = sessionStorage.getItem("server")
    buttons.forEach(name => {
        document.querySelectorAll('#' + name).forEach(button => {
            button.disabled = sessionStorage.getItem(button) ? true : false
            if(serverStarted !== null && name === serverStarted){
                console.log("ENTRA name = ",name)
                initializeServer(name)
            }
        })
    })
}
function clickSendButton(button){
    if(sessionStorage.getItem("server") === null){
        alert("PLEASE SELECT A SERVER")
        return
    }
    const data = {
        GetAllPlayers: callGetAllPlayers,
        GetPlayer: callGetPlayer,
        AddPlayer: callAddPlayer,
        DeletePlayer: callDeletePlayer,
        GetAllTeams: callGetAllTeams,
        GetTeam: callGetTeam,
        AddTeam: callAddTeam,
        DeleteTeam: callDeleteTeam
    }
    data[button.getAttribute("method")]()
}

function callGetAllPlayers(){
    console.log("callGetAllPlayers")
    doCall()
}

function callGetPlayer(){
    console.log("callGetPlayer")
}

function callAddPlayer(){
    console.log("callAddPlayer")
}

function callDeletePlayer(){
    console.log("callDeletePlayer")
}

function callGetAllTeams(){
    console.log("callGetAllTeams")
}

function callGetTeam(){
    console.log("callGetTeam")
}

function callAddTeam(){
    console.log("callAddTeam")
}

function callDeleteTeam(){
    console.log("callDeleteTeam")
}

function doCall(url, port){
    console.log("server = ",sessionStorage.getItem("server"))
    buttons.forEach(button => {
        var a = sessionStorage.getItem(button)
        console.log(`${button} = ${a}`)
    })
    
    fetch()
}

function updateTextArea(response){

    document.querySelectorAll('#responseTextArea').forEach(textArea => {
        textArea.value = response
    })
}
