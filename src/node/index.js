const client = require("./client/client");

/*client.getUser({id:1}, (error, response) => {
    if(error) console.log(error)
    else console.log(response)
})

client.CreateUser({name:"Juan"}, (error, response) => {
    if(error) console.log(error)
    else console.log(response)
})

client.getAllUsers({}, (error, response) => {
    if(error) console.log(error)
    else console.log(response)
})*/

getData = () => {
    let call = client.getData({id:1})
    var array = []
    call.on('data', function (response) {
        array.push(response.data.toString())
    })
    call.on('end', function () {
        console.log("Data from user 1 = ",array.join(''))
    })
}

/*addData = () => {
    let call = client.addData(function(){})
    const str = "Nueva cadena de texto"
    for(const c of str){
        call.write({id:1, data: Buffer.from(c, 'utf8')})
    }
    call.end()
}

exchangeData = () => {
    let call = client.ExchangeData()
    // Read
    let array = []
    call.on('data', function (response){
        array.push(response.data.toString())
    })
    call.on('end', function () {
        console.log("Exchange data response = ",array.join(''))
    })
    //Write
    const str = "Exchanging data"
    for(const c of str){
        call.write({id:1, data: Buffer.from(c, 'utf8')})
    }
    call.end()
}
*/
//addData()
getData()
//exchangeData()


