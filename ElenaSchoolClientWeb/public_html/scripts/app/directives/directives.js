/* global app */

app.filter('date', function ($filter)
{
    return function (input)
    {
        if (input === null) {
            return '';
        }

        var _date = $filter('date')(new Date(input), 'MMM dd yyyy');

        return _date.toUpperCase();
    };
});