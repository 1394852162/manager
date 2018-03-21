/**
 * Created by 陶鹏飞 on 2017/8/4.
 */
angular.module('cBoard').config(['$stateProvider', function ($stateProvider) {
    $stateProvider
        //管理界面控制路由配置
        .state('config', {
            url: '/config',
            abstract: true,
            template: '<div ui-view></div>'
        })
        //员工路由配置
        .state('config.emp', {
            url: '/emp',
            templateUrl: 'org/cboard/view/config/emp.html',
            controller: 'empCtrl'
        })
        .state('config.dept', {
            url: '/dept',
            templateUrl: 'org/cboard/view/config/dept.html',
            controller: 'deptCtrl'
        })
        .state('config.batch', {
            url: '/batch',
            templateUrl: 'org/cboard/view/config/batch.html',
            controller: 'batchCtrl'
        })
        .state('config.collar', {
            url: '/collar',
            templateUrl: 'org/cboard/view/config/collar.html',
            controller: 'collarCtrl'
        })
        .state('config.empticket', {
            url: '/empticket',
            templateUrl: 'org/cboard/view/config/empticket.html',
            controller: 'empticketCtrl'
        })
        .state('config.verification', {
            url: '/verification',
            templateUrl: 'org/cboard/view/config/verification.html',
            controller: 'verificationCtrl'
        })
        .state('config.vip', {
            url: '/vip',
            templateUrl: 'org/cboard/view/config/vip.html',
            controller: 'vipCtrl'
        })
        .state('config.authority', {
            url: '/authority',
            templateUrl: 'org/cboard/view/config/authority.html',
            controller: 'authorityCtrl'
        });
    }
]);

angular.module('cBoard').factory('sessionHelper', ["$rootScope", function ($rootScope) {
    var sessionHelper = {
        responseError: function (response) {
            if (response.status == -1) {
                window.location.href = "/";
            } else {
                return response;
            }
        }
    };
    return sessionHelper;
}]);


angular.module('cBoard').config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function (data) {
        /**
         * The workhorse; converts an object to x-www-form-urlencoded serialization.
         * @param {Object} obj
         * @return {String}
         */
        var param = function (obj) {
            var query = '';
            var name, value, fullSubName, subName, subValue, innerObj, i;

            for (name in obj) {
                value = obj[name];

                if (value instanceof Array) {
                    for (i = 0; i < value.length; ++i) {
                        subValue = value[i];
                        fullSubName = name + '[' + i + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value instanceof Object) {
                    for (subName in value) {
                        subValue = value[subName];
                        fullSubName = name + '[' + subName + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value !== undefined && value !== null) {
                    query += encodeURIComponent(name) + '='
                        + encodeURIComponent(value) + '&';
                }
            }

            return query.length ? query.substr(0, query.length - 1) : query;
        };

        return angular.isObject(data) && String(data) !== '[object File]'
            ? param(data)
            : data;
    }];

    $httpProvider.interceptors.push('sessionHelper');

});


angular.module('cBoard').config(function ($translateProvider, $translatePartialLoaderProvider) {
    $translatePartialLoaderProvider.addPart('cboard');
    $translateProvider.useLoader('$translatePartialLoader', {
        urlTemplate: './i18n/{lang}/{part}.json'
    });

    // console.log(settings.preferredLanguage);
    // $translateProvider.preferredLanguage(settings.preferredLanguage);
    /*$translateProvider.preferredLanguage(function () {
        return "en";
    });*/
    $translateProvider.preferredLanguage(
        (function () {
            // return "en";
            /*console.log(document.URL.split('?')[1].split("=")[1]);
            console.log(document.URL.split('?')[1].split("=")[1].substr(0,2));*/
            var thisURL = document.URL;
            var getval =thisURL.split('?')[1];
            var showval= getval.split("=")[1];
            return showval.substr(0,2);
        })()
    );
});