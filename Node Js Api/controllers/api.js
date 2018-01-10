/**
 * @file Credit Middleware for an Api Http Route
 * @author Victor Tripeno
 * @since 2019-01-02
 */

const express = require('express');
const router = express.Router();
const rp = require('request-promise');

/**
 * @param {object} app - Express object.
 * @returns {object} the express HTTP
 */
module.exports = function(app) {

    // Configurarion
    const config = app.get('config');

    // Validators
    const validatorBody = require('../middlewares/validator_body')(app);

    /**
     * Route POST to send the credit information for the Camel.
     * @return {object} The message of success or failure for call Camel
     */
    router.post('/credit', [validatorBody, async(req, res, next) => {

        const opt = {
            method: 'POST',
            uri: config.url_camel,
            body: req.body,
            headers: {
                'Content-Type': 'application/json'
             },
            json: true
        };

        rp(opt);
        console.log(opt);

        res.json({ status: "message sent" });

    }]);

    return router;
}
