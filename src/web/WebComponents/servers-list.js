//import render from './render';

export default class serversList extends HTMLElement {
    constructor() {
        super();
        const type = this.getAttribute('type')
        console.log("type = ",type)
        this.render()
    }

    render(){
        this.innerHTML = `
        <div>
        Start one of these servers:
        <br>
        (The request will be done to the firstserver up in the list)

        <ul class="timeline">
          <li>
          <div id="nodeServerDiv" class="inline">
            <button
              id="nodeServer"
              class="serverButton"
              onclick="initializeNodeServer()"
              ${sessionStorage.getItem("nodeServer")===true?'disabled':''}
            >Node.JS</button>
            </div>
          </li>
          <li>
          <div id="csharpServerDiv" class="inline">
            <button
              id="csharpServer"
              class="serverButton"
              onclick="initializeCsharpServer()"
              ${sessionStorage.getItem("csharpServer")===true?'disabled':''}
            >
              c#
            </button>
            </div>
          </li>
          <li>
          <div id="pythonServerDiv" class="inline">
            <button
              id="pythonServer"
              class="serverButton"
              onclick="initializePythonServer()"
              ${sessionStorage.getItem("pythonServer")===true?'disabled':''}
            >
              Python
            </button>
            </div>
          </li>
          <li>
          <div id="javaServerDiv" class="inline">
            <button
              id="javaServer"
              class="serverButton"
              onclick="initializeJavaServer()"
              ${sessionStorage.getItem("javaServer")===true?'disabled':''}
            >
              Java
            </button>
            </div>
          </li>
        </ul>
      </div>
        `
    }
    
}

customElements.define('servers-list', serversList);
