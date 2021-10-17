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
            db.run(`CREATE TABLE IF NOT EXISTS team (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                city varchar(255),
                name varchar(255)
                )`)
            db.run(`CREATE TABLE IF NOT EXISTS player (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name varchar(255),
                lastname varchar(255),
                position varchar(255),
                age INTEGER,
                team INTEGER,
                FOREIGN KEY(team) REFERENCES team(id)
                )`)

            db.run('INSERT INTO team(city, name) VALUES (?, ?)', ['c1', 'cityname1'])
            db.run('INSERT INTO team(city, name) VALUES (?, ?)', ['c2', 'cityname2'])
            db.run('INSERT INTO player(name, lastname, position, age, team) VALUES (?, ?, ?, ?, ?)', ['playername1', 'ln1', 'p1', 1, 1])
            db.run('INSERT INTO player(name, lastname, position, age, team) VALUES (?, ?, ?, ?, ?)', ['playername2', 'ln2', 'p2', 2, 1])
            db.run('INSERT INTO player(name, lastname, position, age, team) VALUES (?, ?, ?, ?, ?)', ['playername3', 'ln3', 'p3', 3, 2])   
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
