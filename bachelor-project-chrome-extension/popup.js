const openPage = function (url) {
    chrome.tabs.create({ url });
};

chrome.storage.sync.get('onCheapestSite', function (onCheapestSite) {
    var message = document.getElementById('message');
    if (onCheapestSite.onCheapestSite) {
        message.innerHTML = 'You are already looking at the cheapest offer!';
    } else {
        chrome.storage.sync.get('cheaperPriceUrl', function (cheaperPriceUrl) {
            if (String(cheaperPriceUrl.cheaperPriceUrl).length != 0) {
                var url = new URL(cheaperPriceUrl.cheaperPriceUrl);

                let message = document.getElementById('message');

                message.innerHTML = 'You can find this product cheaper at <a href="' + url + '">' + url.hostname + '</a>!';
                message.setAttribute("id", "cheaper_url_openable");
                message.addEventListener("click", function () {
                    console.log('clicked cheaper_url');
                    var linkHref = String(url);
                    openPage(linkHref);
                });
            }
        });
    }
});

// document.getElementById('fullwebsite').addEventListener("click", function () {
//     console.log('clicked fullwebsite');
//     chrome.tabs.create('www.google.com');
// });