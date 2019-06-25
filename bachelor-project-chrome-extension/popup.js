// Variable definitions

var currentTabId;

// Method definitions

function updatePopupHtml() {
    chrome.storage.sync.get('supportedSiteData', function (data) {
        var message = document.getElementById('message');
        console.log(data.supportedSiteData);
        console.log(currentTabId);
        var thisTabData = data.supportedSiteData.find(obj => {
            return obj.tabId === currentTabId;
        });
        console.log(thisTabData);
        if (thisTabData != undefined) {
            if (thisTabData.onCheapestSite) {
                message.innerHTML = 'You are already looking at the cheapest offer!';
            } else {
                if (String(thisTabData.cheaperPriceUrl).length != 0) {
                    var url = new URL(thisTabData.cheaperPriceUrl);

                    let message = document.getElementById('message');

                    message.innerHTML = 'You can find this product cheaper at <a href="' + url + '">' + url.hostname + '</a>!';
                    message.setAttribute("id", "cheaper_url_openable");
                    message.addEventListener("click", function () {
                        console.log('clicked cheaper_url');
                        var linkHref = String(url);
                        openPage(linkHref);
                    });
                }
            }
        }
    });
}

function openPage(url) {
    chrome.tabs.create({ url });
}

// Method calls

chrome.tabs.onActivated.addListener(function (activeInfo) {
    currentTabId = activeInfo.tabId;
    updatePopupHtml();
});

chrome.tabs.query({ active: true, currentWindow: true }, function (tabs) {
    currentTabId = tabs[0].id;

    var full = document.getElementById('full_website');
    full.addEventListener("click", function () {
        console.log('clicked cheaper_url');
        var linkHref = String(full.getAttribute('href'));
        openPage(linkHref);
    });
});

updatePopupHtml();