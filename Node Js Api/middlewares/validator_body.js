/**
 * @file Module to validate Body
 * @author Victor Tripeno
 * @since 2017-12-22
 */

const validator = require('../validator');
const _ = require('lodash');

/**
 * @param {object} app - Express object.
 * @returns {object} the validator functions
 */
module.exports = function(app){

    /**
     * Middleware validation for body
     * @param {object} req the express middleware request object
     * @param {object} res the express middleware reponse object
     * @param {object} next the express middleware function to continued the middleware processing
     */
    return (req, res, next) => {
        const {
            checkField,
            checkReportForREST
        } = validator(req);

        const fieldsAllowed = ['cpf', 'value', 'anual_percentage'];

        checkField('body', `ATTRIBUTE IS NOT ALLOWED, PERMITED ATTRIBUTES ONLY: ${fieldsAllowed}`).custom(function(body) {
            return _.chain(Object.keys(body)).difference(fieldsAllowed).isEmpty().value();
        });

        if (checkReportForREST()) {
            var err = checkReportForREST();
            err.status = 400;
            next(err);
        } else {
            next();
        }
    };
};
