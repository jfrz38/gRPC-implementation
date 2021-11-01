export default class rpcCalls extends HTMLElement {
    constructor() {
        super();
        this.render()
        document.dispatchEvent(new CustomEvent("initialize-buttons",{
            bubbles: true
        }))
    }

    render() {
        this.innerHTML = `
        <div>
          <table>
            <tr>
              <th>RPC Call</th>
              <th>RPC Request</th>
              <th>Send call</th>
            </tr>
            ${this.createRows()}
          </table>
            <h3>Response</h3>
          <textarea id="responseTextArea" readonly></textarea>
          <div  style="font-size: 10px;">
            <p>* Calls do not use gRPC to communicate between frontend-backend because the needed of a proxy. To implements both gRPC producer and consumer on the backend, the program do an HTTP request and the communication using gRPC is done in the backend.</p>
            <p>A gRPC implementation where client is the frontend can be consulted <a href="https://github.com/jfrz38/ConnectionServicesTFM/tree/main/services/search">here</a>.</p>
            <p>By the way this approach allows to know how to implement a REST API in different languagues although the best practices have not been followed.</p>
          </div>
        </div>`
    }

    createRows() {
        const data = [
            { call: "GetAllPlayers" },
            { call: "GetPlayer", sendRequest: [{value:"id",type:"integer"}] },
            { call: "AddPlayer", sendRequest: [{value:"id",type:"integer"}, {value:"name",type:"string"}, {value:"lastname",type:"string"}, {value:"age",type:"integer"}, {value:"team",type:"integer"}] },
            { call: "DeletePlayer", sendRequest: [{value:"id",type:"integer"}] },
            { call: "GetAllTeams" },
            { call: "GetTeam", sendRequest: [{value:"id",type:"integer"}] },
            { call: "AddTeam", sendRequest: [{value:"id",type:"integer"}, {value:"name",type:"string"}, {value:"city",type:"string"}] },
            { call: "DeleteTeam", sendRequest: [{value:"id",type:"integer"}] }]

        
        var html = "";
        data.forEach(d => {
            html +=
            `<tr>
                <td>${d.call}</td>
                <td>${this.addInputs(d)}</td>
                <td><button method="${d.call}" onclick="clickSendButton(this)">SEND</button></td>
            </tr>`
                
        })
        return html;
    }

    addInputs(data){
        var html = ''
        if(data.sendRequest === undefined){
            html += '<input disabled> </input>'
        }else{
            html += `<input placeholder="${this.createInputPlaceholder(data.sendRequest)}"> </input>`
        }
        return html;
    }

    createInputPlaceholder(data){
        return `{${data.map(m => m.value + `:` + m.type).join(',')}}`
    }

    handleEvent(ev) {
        console.log(ev)
    }
}

customElements.define('rpc-calls', rpcCalls);
