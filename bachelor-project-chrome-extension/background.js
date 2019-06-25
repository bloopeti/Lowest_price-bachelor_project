// Variable definitions

let supportedDomains = ['www.emag.ro', 'www.pcgarage.ro'];

// Method definitions

function checkSupportedDomain(tab) {
    var url = new URL(tab.url);
    var domain = url.hostname;
    if (supportedDomains.includes(domain)) {
        console.log(domain + " is supported!");
        chrome.pageAction.show(tab.id);

        removeSupportedSiteEntry(tab.id);
        if (!(url.pathname === '/')) {
            checkCheaperPrice(url, tab.id);
        }
    }
}

function checkCheaperPrice(url, tabId) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:9906/app/productUrl/cheapest", true);
    xhr.setRequestHeader('Content-type', 'text/plain');
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // console.log(xhr.responseText);
            if (!(xhr.responseText === 'No similar url found!')) {
                if (xhr.responseText.includes(url.hostname + url.pathname)) {
                    console.log("You're on the website with the cheapest price for this product!");
                    addSupportedSiteEntry({
                        tabId: tabId,
                        onCheapestSite: true,
                        cheaperPriceUrl: '',
                    });

                } else {
                    console.log("This product can be found at a cheaper price at '" + xhr.responseText + "'!");
                    addSupportedSiteEntry({
                        tabId: tabId,
                        onCheapestSite: false,
                        cheaperPriceUrl: xhr.responseText,
                    });
                }
            }
        }
    }
    xhr.send(url.pathname);
}

function addSupportedSiteEntry(entry) {
    chrome.storage.sync.get('supportedSiteData', function (data) {
        if (!(isEmpty(data))) {
            let currentData = data.supportedSiteData;
            currentData.push(entry);
            chrome.storage.sync.set(
                {
                    supportedSiteData: currentData
                });
        } else {
            chrome.storage.sync.set(
                {
                    supportedSiteData: [entry]
                });
        }
    });
}

function removeSupportedSiteEntry(tabId) {
    chrome.storage.sync.get('supportedSiteData', function (data) {
        if (!(isEmpty(data))) {
            let currentData = data.supportedSiteData;
            let tabDataToRemove = data.supportedSiteData.find(obj => {
                return obj.tabId === tabId;
            });
            let index = currentData.indexOf(tabDataToRemove);
            if (index >= 0) {
                currentData.splice(index, 1);
                chrome.storage.sync.set(
                    {
                        supportedSiteData: currentData
                    });
            }
        }
    });
}

function isEmpty(obj) {

    // null and undefined are "empty"
    if (obj == null) return true;

    // Assume if it has a length property with a non-zero value
    // that that property is correct.
    if (obj.length > 0) return false;
    if (obj.length === 0) return true;

    // If it isn't an object at this point
    // it is empty, but it can't be anything *but* empty
    // Is it empty?  Depends on your application.
    if (typeof obj !== "object") return true;

    // Otherwise, does it have any properties of its own?
    // Note that this doesn't handle
    // toString and valueOf enumeration bugs in IE < 9
    for (var key in obj) {
        if (hasOwnProperty.call(obj, key)) return false;
    }

    return true;
}

// Method calls

chrome.runtime.onInstalled.addListener(function () {
    chrome.storage.sync.clear();
});

chrome.tabs.onUpdated.addListener(function (tabId, changeInfo, tab) {
    if (changeInfo.status === "complete") {
        console.log("Just completed updating " + tab.url);
        checkSupportedDomain(tab);
    }
});

chrome.tabs.onRemoved.addListener(function (tabId, removeInfo) {
    removeSupportedSiteEntry(tabId);
});