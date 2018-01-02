/**
 * @file 
 * @author @tripeno
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

    /**
     * 
     */
    router.post('/financial', async(req, res, next) => {
        
        const opt = {
            method: 'POST',
            uri: url.camel,
            body: {
                data: req.body
            },
            headers: { },
            json: true
        };

        rp(opt);

        res.json({ status: "message sent" });
        
    });

    return router;
}
