const assert = require('assert/strict');
const client = require("./client/client");

const playerToAdd = { name: "name3", lastname: "ln3", position: "p3", age: 3, team: { id: 1 } }

client.getAllPlayers({}, (error, response) => {
    console.log("GET all players")
    if (error) console.log(error)
    else assert.deepEqual(['1', '2'], response.players.map(player => player.id))
})

client.addPlayer(playerToAdd, (error, response) => {
    console.log("Add player n.3")
    if (error) console.log(error)
})

client.getAllPlayers({}, (error, response) => {
    console.log("Check if player n.3 is returned")
    if (error) console.log(error)
    else assert.deepEqual(['1', '2', '3'], response.players.map(player => player.id))
})

client.deletePlayer({ id: 3 }, (error, response) => {
    console.log("Remove player n.3")
    if (error) console.log(error)
})

client.getAllPlayers({}, (error, response) => {
    console.log("Check if player n.3 is returned")
    if (error) console.log(error)
    else assert.deepEqual(['1', '2'], response.players.map(player => player.id))
})

