const express = require('express');
const bodyParser = require('body-parser');

let app = express();

(async () => {
    return {
        config: await require('./config')(app)
    };
})().then(({config}) => {
    // Constrollers
    const BASE_PATH = '/api-contract';
    app.use(BASE_PATH, require('./controllers/api')(app));

    // Starting server
    const port = app.get('config').port || 4000;
    app.listen(port);
}).catch((err) => {
    if (err) {
        logger.crit(`Error Running an API Automation Server. Error: ${JSON.stringify(err)}`);
    }
});
