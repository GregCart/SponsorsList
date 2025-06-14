// const backendUrl = process.env.BACKEND_URL;
const backendUrl = "https://gq7p4jvz3wgq6neyn4toygnzni0zmswh.lambda-url.us-east-1.on.aws/";


async function healthCheck() {
    let check = false;

    await fetch(backendUrl + "/").then((resp) => {
        if (resp.text() == "Hello World!") {
            check = true;
        }
    }).finally(() =>{
        return check;
    });
}

async function getSponsorsList() {
    let data;

    await fetch(backendUrl + "/getItemList").then((resp) => {
        data = resp.json();
    });

    return data;
}

async function addSponsorItem(data) {
    let resp;

    await fetch(backendUrl + "/addToList/sponsorsLink", {
        method: 'Post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then(responseData => {
        console.log('Success:', responseData);
        resp = responseData;
      })
      .catch(error => {
        console.error('Error:', error);
      });

      return resp;
}