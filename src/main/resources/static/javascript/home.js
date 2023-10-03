// Cookie
const cookieArr = document.cookie.split("=");
const userId = cookieArr[1];

// DOM Elements
const submitForm = document.getElementById("vehicle-form");
const vehicleContainer = document.getElementById("vehicle-container");

// Modal Elements
let vehicleBody = document.getElementById(`vehicle-body`);
let updateVehicleBtn = document.getElementById('update-vehicle-button');

const headers = {
    'Content-Type': 'application/json'
};

const baseUrl = "http://localhost:8080/api/v1/vehicles/";

const handleSubmit = async (e) => {
    e.preventDefault();
    let bodyObj = {
        vehicleName: document.getElementById("vehicle-input").value
    };
    await addVehicle(bodyObj);
    document.getElementById("vehicle-input").value = '';
};

async function addVehicle(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    }).catch(err => console.error(err.message));
    if (response.status == 200) {
        return getVehicles(userId);
    }
}

async function getVehicles(userId) {
    try {
        const response = await fetch(`${baseUrl}user/${userId}`, {
            method: "GET",
            headers: headers
        });
        const data = await response.json();
        createVehicleCards(data);
    } catch (err) {
        console.error(err);
    }
}

async function handleDelete(vehicleId) {
    try {
        await fetch(baseUrl + vehicleId, {
            method: "DELETE",
            headers: headers
        });
        getVehicles(userId);
    } catch (err) {
        console.error(err);
    }
}

async function getVehicleById(vehicleId) {
    try {
        const response = await fetch(baseUrl + vehicleId, {
            method: "GET",
            headers: headers
        });
        const data = await response.json();
        populateModal(data);
    } catch (err) {
        console.error(err.message);
    }
}

async function handleVehicleEdit(vehicleId) {
    let bodyObj = {
        id: vehicleId,
        vehicleName: vehicleBody.value
    };

    console.log(bodyObj);

    try {
        await fetch(baseUrl, {
            method: "PUT",
            body: JSON.stringify(bodyObj),
            headers: headers
        });
        getVehicles(userId);
    } catch (err) {
        console.error(err);
    }
}

const createVehicleCards = (array) => {
    vehicleContainer.innerHTML = '';
    array.forEach(obj => {
        let vehicleCard = document.createElement("div");
        vehicleCard.classList.add("m-2");

        vehicleCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.vehicleName}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getVehicleById(${obj.id})" type="button" class="btn btn-primary"
                            data-bs-toggle="modal" data-bs-target="#vehicle-edit-modal">
                            Edit
                        </button>
                        <button onclick="openGasFillingModal(${obj.id})" type="button" class="btn btn-success"
                            data-bs-toggle="modal" data-bs-target="#gas-filling-modal">
                            Add Gas Filling
                        </button>
                    </div>
                </div>
            </div>
        `;
        vehicleContainer.append(vehicleCard);
    });
};

// ... (previous code)

let addGasFillingButton;
let gasFillingModal = new bootstrap.Modal(document.getElementById('gas-filling-modal'));

let gasFillingModalShown = false;

function openGasFillingModal(vehicleId) {
    // Optionally, you can populate the modal with information related to the selected vehicle.
    document.getElementById("gas-filling-volume").value = "";
    document.getElementById("gas-filling-mileage").value = "";

    // To open the Gas Filling modal, trigger the modal to show:
    gasFillingModal.show();

    if (!gasFillingModalShown) {
        addGasFillingButton = document.getElementById('add-gas-filling-button');
        addGasFillingButton.addEventListener('click', () => {
            handleGasFillingSubmit(vehicleId);
            gasFillingModal.hide(); // Hide the modal after adding gas filling
        });
        gasFillingModalShown = true;
    }
}


async function handleGasFillingSubmit(vehicleId) {
    const gasFillingVolume = document.getElementById("gas-filling-volume").value;
    const gasFillingMileage = document.getElementById("gas-filling-mileage").value;

    const gasFillingData = {
        fillingVolume: parseFloat(gasFillingVolume),
        currentMileage: parseFloat(gasFillingMileage),
    };

    const apiUrl = `http://localhost:8080/api/v1/gasfillings/vehicle/${vehicleId}`;

    try {
        const response = await fetch(apiUrl, {
            method: "POST",
            body: JSON.stringify(gasFillingData),
            headers: {
                "Content-Type": "application/json",
            },
        });
        if (response.status === 200) {
            // Gas filling added successfully, you can perform any additional actions here if needed
            console.log("Gas filling added successfully.");
        } else {
            // Handle errors here, if necessary
            console.error("Failed to add gas filling.");
        }
    } catch (err) {
        console.error(err);
    }

    // Clear input fields after submission
    document.getElementById("gas-filling-volume").value = "";
    document.getElementById("gas-filling-mileage").value = "";
    gasFillingModal.hide();
}

// ... (rest of your code)




function handleLogout() {
    let c = document.cookie.split(";");
    for (let i in c) {
        document.cookie = /^[^=]+/.exec(c[i])[0] + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}

const populateModal = (obj) => {
    vehicleBody.innerText = '';
    vehicleBody.innerText = obj.vehicleName;
    updateVehicleBtn.setAttribute('data-vehicle-id', obj.id);
}

getVehicles(userId);

submitForm.addEventListener("submit", handleSubmit);

updateVehicleBtn.addEventListener("click", (e) => {
    let vehicleId = e.target.getAttribute('data-vehicle-id');
    handleVehicleEdit(vehicleId);
});
