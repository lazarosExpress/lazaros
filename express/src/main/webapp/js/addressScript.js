document.addEventListener("DOMContentLoaded", () => {
    fetchAddresses();
    fetchProvinces();
});

function fetchAddresses() {
    fetch("/express/AddressController?action=list")
        .then(response => response.json())
        .then(data => {
            const addresses = document.getElementById("addresses");
            addresses.innerHTML = "";
            data.forEach(address => {
                const row = document.createElement("div");
                row.classList.add("address-card");
                row.innerHTML = `
                    <h3>${address.address_title}</h3>
                    <p>${address.address_customerFirstName} ${address.address_customerLastName}</p>
                    <p>${address.address_customerPhoneNumber}</p>
                    <p>${address.address_description}</p>
                    <p>İl: ${address.provinces_name}</p>
                    <div class="button-container">
                        <button class="edit-button" onclick="editAddress(${address.address_id})">Adresi Düzenle</button>
                        <button class="delete-button" onclick="deleteAddress(${address.address_id})"><i class="bi bi-trash"></i> Sil</button>
                    </div>
                `;
                addresses.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching addresses:", error));
}

function fetchProvinces() {
    fetch("/express/AddressController?action=listProvinces")
        .then(response => response.json())
        .then(data => {
            const provinceSelect = document.getElementById("province");
            data.forEach(province => {
                const option = document.createElement("option");
                option.value = province.provinces_id; // İl id'sini buradan alıyoruz
                option.text = province.provinces_name;
                provinceSelect.appendChild(option);
            });
            // İlçe, Semt ve Mahalle seçimlerini gizle
            document.getElementById("district-container").style.display = 'none';
            document.getElementById("subdistrict-container").style.display = 'none';
            document.getElementById("neighbourhood-container").style.display = 'none';
        })
        .catch(error => console.error("Error loading provinces:", error));
}

function loadDistricts(provinceId) {
    if (provinceId) {
        fetch(`/express/AddressController?action=listDistricts&province_id=${provinceId}`)
            .then(response => response.json())
            .then(data => {
                const districtSelect = document.getElementById("district");
                districtSelect.innerHTML = "<option value=''>Seçiniz</option>";
                data.forEach(district => {
                    const option = document.createElement("option");
                    option.value = district.district_id;
                    option.text = district.district_name;
                    districtSelect.appendChild(option);
                });
                // İlçe seçimini görünür yap, Semt ve Mahalle seçimlerini gizle
                document.getElementById("district-container").style.display = 'block';
                document.getElementById("subdistrict-container").style.display = 'none';
                document.getElementById("neighbourhood-container").style.display = 'none';
            })
            .catch(error => console.error("Error loading districts:", error));
    } else {
        document.getElementById("district-container").style.display = 'none';
        document.getElementById("subdistrict-container").style.display = 'none';
        document.getElementById("neighbourhood-container").style.display = 'none';
    }
}

function loadSubDistricts(districtId) {
    if (districtId) {
        fetch(`/express/AddressController?action=listSubDistricts&districts_id=${districtId}`)
            .then(response => response.json())
            .then(data => {
                const subDistrictSelect = document.getElementById("subdistrict");
                subDistrictSelect.innerHTML = "<option value=''>Seçiniz</option>";
                data.forEach(subDistrict => {
                    const option = document.createElement("option");
                    option.value = subDistrict.subDistricts_id;
                    option.text = subDistrict.subDistricts_name;
                    subDistrictSelect.appendChild(option);
                });
                // Semt seçimini görünür yap, Mahalle seçimini gizle
                document.getElementById("subdistrict-container").style.display = 'block';
                document.getElementById("neighbourhood-container").style.display = 'none';
            })
            .catch(error => console.error("Error loading subdistricts:", error));
    } else {
        document.getElementById("subdistrict-container").style.display = 'none';
        document.getElementById("neighbourhood-container").style.display = 'none';
    }
}

function loadNeighbourhoods(subDistrictId) {
    if (subDistrictId) {
        fetch(`/express/AddressController?action=listNeighbourhood&subDistricts_id=${subDistrictId}`)
            .then(response => response.json())
            .then(data => {
                const neighbourhoodSelect = document.getElementById("neighbourhood");
                neighbourhoodSelect.innerHTML = "<option value=''>Seçiniz</option>";
                data.forEach(neighbourhood => {
                    const option = document.createElement("option");
                    option.value = neighbourhood.neighbourhood_id;
                    option.text = neighbourhood.neighbourhood_name;
                    neighbourhoodSelect.appendChild(option);
                });
                // Mahalle seçimini görünür yap
                document.getElementById("neighbourhood-container").style.display = 'block';
            })
            .catch(error => console.error("Error loading neighbourhoods:", error));
    } else {
        document.getElementById("neighbourhood-container").style.display = 'none';
    }
}

function editAddress(id) {
    window.location.href = `/express/AddressController?action=edit&id=${id}`;
}

function deleteAddress(id) {
    if (confirm('Bu adresi silmek istediğinizden emin misiniz?')) {
        fetch(`/express/AddressController?action=delete&id=${id}`)
            .then(() => {
                fetchAddresses();
            })
            .catch(error => console.error("Error deleting address:", error));
    }
}

function showAddAddressModal() {
    document.getElementById('addAddressModal').style.display = 'block';
}

function closeAddAddressModal() {
    document.getElementById('addAddressModal').style.display = 'none';
}
