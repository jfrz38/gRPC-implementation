const assert = require('assert/strict');
const client = require("./client/client");

/*client.getAllTeams({}, (error, response) => {
    if(error) console.log(error)
    else console.log(response)
})*/

client.getTeam({id:1}, (error, response) => {
    assert.deepEqual(error, null)
    assert.deepEqual(response.id, 1)
})

client.getTeam({id:10}, (error, response) => {
    // Error not found
    assert.deepEqual(error.code, 5)
})

client.getPlayer({id:1}, (error,response) => {
    assert.deepEqual(error, null)
    assert.deepEqual(response.id, 1)
})

client.getPlayer({id:10}, (error,response) => {
    // Error not found
    assert.deepEqual(error.code, 5)
})

client.deleteTeam({id:1}, (error, response) => {
    assert.deepEqual(error,null)
})

client.getTeam({id:1}, (error, response) => {
    // Error not found
    assert.deepEqual(error.code, 5)
})

client.deletePlayer({id:1}, (error, response) => {
    assert.deepEqual(error,null)
})

client.getPlayer({id:1}, (error,response) => {
    // Error not found
    assert.deepEqual(error.code, 5)
})

client.addPlayer({name:'n3',lastname:'ln3',position:'p3',age:3, team:1}, (error, response) => {
    assert.deepEqual(error, null)
})

client.getPlayer({id:3}, (error, response) => {
    assert.deepEqual(error, null)
    assert.deepEqual(response.id, 3)
})

client.addTeam({name:'t2', city:'c2'}, (error, response) => {
    assert.deepEqual(error, null)
})

client.getTeam({id:2}, (error, response) => {
    assert.deepEqual(error, null)
    assert.deepEqual(response.id, 2)
})

