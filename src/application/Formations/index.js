var express = require('express');
var app = express();
var mysql = require('mysql');




var connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'dbgestionformations'
});

app.use(express.static(__dirname + '/Style'));
app.use(express.static(__dirname + '/Includes'));


app.set('view engine', 'ejs');

connection.connect(function (err) {
    if (err) throw err
    console.log('You are now connected with mysql database...')
})

var server = app.listen(3000, "127.0.0.1", function () {
    var host = server.address().address
    var port = server.address().port
    console.log("Example app listening at http://%s:%s", host, port)
});

app.get('/', function (req, rep) {
    rep.render('formations');

})

app.get('/(:id)', function (req, rep, next) {

    let id = req.params.id;

    connection.query('SELECT * FROM session WHERE formation = ' +'"'+ id+'"', function (err, rows, fields) {
        if (err) throw err

        if (rows.length <= 0) {
            console.log("il n'existe aucune Session")
            throw err
            
        }
        else {
            let obj = {
                session: rows
            };
            rep.render('Formations', obj);
        }
    })
})

