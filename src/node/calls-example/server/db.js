var sqlite3 = require('sqlite3').verbose();
let db = null

module.exports.connect = () => {
    db = new sqlite3.Database(':memory:', (err) => {
        if (err) {
            console.log(err.message)
            return
        }
        console.log("connected to db")
        db.serialize(function () {
            db.run(`CREATE TABLE IF NOT EXISTS User (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name varchar(255),
                data binary
                )`)

            db.run('INSERT INTO User(name, data) VALUES (?, ?)', ['Pepe', 'character string for Pepe'])
            db.run('INSERT INTO User(name, data) VALUES (?, ?)', ['Paco', 'character string for Paco'])
            db.run('INSERT INTO User(name, data) VALUES (?, ?)', ['Manolo', 'character string for Manolo'])
        })
    });
}
module.exports.close = () => {
    db.close((err) => {
        if (err) console.log(err.message)
        else {
            console.log("closed db")
        }
    })
}

module.exports.getAllUsers = async () => {
    return new Promise((resolve, reject) => {
        db.all(`SELECT * FROM User u`, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.getUser = async (id) => {
    return new Promise((resolve, reject) => {
        db.get(`SELECT * FROM User u WHERE u.id = ${id}`, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.createUser = async (name) => {
    return new Promise((resolve, reject) => {
        db.run('INSERT INTO User(name, data) VALUES (?, ?)', [name], (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.getData = async (id) => {
    return new Promise((resolve, reject) => {
        db.get(`SELECT u.data FROM User u WHERE u.id = ${id}`, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.addData = async (id, data) => {
    return new Promise((resolve, reject) => {
        db.run(`UPDATE User SET data = "${data}" WHERE id = ${id}`, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}
/*
module.exports.getTeams = async () => {
    return new Promise((resolve, reject) => {
        db.get(`SELECT * from team t`, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.getTeam = (id) => {
    return new Promise((resolve, reject) => {
        db.get(`SELECT * from team t WHERE t.id = ${id}`, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.getPlayers = () => {
    return new Promise((resolve, reject) => {
        db.all(`SELECT * from player p`, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.getPlayer = (id) => {
    return new Promise((resolve, reject) => {
        db.get(`SELECT * from player p WHERE p.id = ${id}`, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.addPlayer = (player) => {
    return new Promise((resolve, reject) => {
        db.run(`INSERT INTO player(name, lastname, position, age, team) VALUES (?, ?, ?, ?, ?) `,
        [player.name, player.lastname, player.position, player.age, player.team], (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.addTeam = (team) => {
    return new Promise((resolve, reject) => {
        db.run(`INSERT INTO team(city, name) VALUES (?, ?) `,
        [team.city, team.name], (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.deletePlayer = (id) => {
    return new Promise((resolve, reject) => {
        db.run(`DELETE FROM player WHERE id = ?`,id, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}

module.exports.deleteTeam = (id) => {
    return new Promise((resolve, reject) => {
        db.run(`DELETE FROM team WHERE id = ?`,id, (err, response) => {
            if(err) reject(err)
            resolve(response)
        })
    })
}
*/
