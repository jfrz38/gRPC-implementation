const express = require('express')
const bodyParser = require("body-parser")
var app = express()

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

var router = express.Router();
var client = null;
var server = null

router.get("/", (req, res) => {
    if(server == null) server = require('../server/server')
    res.status(200).send()
});

/* PLAYERS */
router.get('/getAllPlayers', (req, res) => {
    initializeClient()
    client.getAllPlayers({}, (error, response) => {
        if(error) res.status(200).send(error)
        else res.status(200).send(response)
    })
})
router.get('/getPlayer/:id', (req, res) => {
    initializeClient()
    client.getPlayer({id:req.params.id}, (error, response) => {
        if(error) res.status(200).send(error)
        else res.status(200).send(response)
    })
})

/* TEAMS */
router.get('/getAllTeams', (req, res) => {
    initializeClient()
    client.getAllTeams({}, (error, response) => {
        if(error) res.status(200).send(error)
        else res.status(200).send(response)
    })
})
router.get('/getTeam/:id', (req, res) => {
    initializeClient()
    client.getTeam({id:req.params.id}, (error, response) => {
        if(error) res.status(200).send(error)
        else res.status(200).send(response)
    })
})

// This is not stateless... :(
function initializeClient(){
    if(client == null) client = require('../client/client')
}

app.use(router);

app.listen(8083);
