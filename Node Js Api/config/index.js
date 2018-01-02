/**
 * @file API Model
 * @author @vrto
 * @since 2017-12-19
 */
/**
 * Configuration Model
 * @param {object} app - Express object.
 */

module.exports = async (app) => {
    if (process.env.NODE_ENV !== 'production') {
        app.set('config', require('./DES'));
    } else {
        app.set('config', require('./PRD'));
    }
    return {};
};
