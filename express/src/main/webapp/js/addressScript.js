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
                        <button class="edit-button" onclick="showEditAddressModal(${address.address_id})">Adresi Düzenle</button>
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
            const editProvinceSelect = document.getElementById("edit_province");
            data.forEach(province => {
                const option = document.createElement("option");
                option.value = province.provinces_id; // İl id'sini buradan alıyoruz
                option.text = province.provinces_name;
                provinceSelect.appendChild(option);

                // Edit formu için de aynı seçenekleri ekleyelim
                const editOption = option.cloneNode(true);
                editProvinceSelect.appendChild(editOption);
            });
            // İlçe, Semt ve Mahalle seçimlerini gizle
            document.getElementById("district-container").style.display = 'none';
            document.getElementById("subdistrict-container").style.display = 'none';
            document.getElementById("neighbourhood-container").style.display = 'none';
            document.getElementById("edit_district-container").style.display = 'none';
            document.getElementById("edit_subdistrict-container").style.display = 'none';
            document.getElementById("edit_neighbourhood-container").style.display = 'none';
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

function loadEditDistricts(provinceId) {
    if (provinceId) {
        fetch(`/express/AddressController?action=listDistricts&province_id=${provinceId}`)
            .then(response => response.json())
            .then(data => {
                const districtSelect = document.getElementById("edit_district");
                districtSelect.innerHTML = "<option value=''>Seçiniz</option>";
                data.forEach(district => {
                    const option = document.createElement("option");
                    option.value = district.district_id;
                    option.text = district.district_name;
                    districtSelect.appendChild(option);
                });
                // İlçe seçimini görünür yap, Semt ve Mahalle seçimlerini gizle
                document.getElementById("edit_district-container").style.display = 'block';
                document.getElementById("edit_subdistrict-container").style.display = 'none';
                document.getElementById("edit_neighbourhood-container").style.display = 'none';
            })
            .catch(error => console.error("Error loading districts:", error));
    } else {
        document.getElementById("edit_district-container").style.display = 'none';
        document.getElementById("edit_subdistrict-container").style.display = 'none';
        document.getElementById("edit_neighbourhood-container").style.display = 'none';
    }
}

function loadEditSubDistricts(districtId) {
    if (districtId) {
        fetch(`/express/AddressController?action=listSubDistricts&districts_id=${districtId}`)
            .then(response => response.json())
            .then(data => {
                const subDistrictSelect = document.getElementById("edit_subdistrict");
                subDistrictSelect.innerHTML = "<option value=''>Seçiniz</option>";
                data.forEach(subDistrict => {
                    const option = document.createElement("option");
                    option.value = subDistrict.subDistricts_id;
                    option.text = subDistrict.subDistricts_name;
                    subDistrictSelect.appendChild(option);
                });
                // Semt seçimini görünür yap, Mahalle seçimini gizle
                document.getElementById("edit_subdistrict-container").style.display = 'block';
                document.getElementById("edit_neighbourhood-container").style.display = 'none';
            })
            .catch(error => console.error("Error loading subdistricts:", error));
    } else {
        document.getElementById("edit_subdistrict-container").style.display = 'none';
        document.getElementById("edit_neighbourhood-container").style.display = 'none';
    }
}

function loadEditNeighbourhoods(subDistrictId) {
    if (subDistrictId) {
        fetch(`/express/AddressController?action=listNeighbourhood&subDistricts_id=${subDistrictId}`)
            .then(response => response.json())
            .then(data => {
                const neighbourhoodSelect = document.getElementById("edit_neighbourhood");
                neighbourhoodSelect.innerHTML = "<option value=''>Seçiniz</option>";
                data.forEach(neighbourhood => {
                    const option = document.createElement("option");
                    option.value = neighbourhood.neighbourhood_id;
                    option.text = neighbourhood.neighbourhood_name;
                    neighbourhoodSelect.appendChild(option);
                });
                // Mahalle seçimini görünür yap
                document.getElementById("edit_neighbourhood-container").style.display = 'block';
            })
            .catch(error => console.error("Error loading neighbourhoods:", error));
    } else {
        document.getElementById("edit_neighbourhood-container").style.display = 'none';
    }
}

function showAddAddressModal() {
    document.getElementById('addAddressModal').style.display = 'block';
}

function closeAddAddressModal() {
    document.getElementById('addAddressModal').style.display = 'none';
}

function showEditAddressModal(id) {
    fetch(`/express/AddressController?action=LISTUPDATEADDRESS&address_id=${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById("edit_address_id").value = data.address_id;
            document.getElementById("edit_address_customerFirstName").value = data.address_customerFirstName;
            document.getElementById("edit_address_customerLastName").value = data.address_customerLastName;
            document.getElementById("edit_address_customerPhoneNumber").value = data.address_customerPhoneNumber;
            document.getElementById("edit_province").value = data.address_province;
            loadEditDistricts(data.address_province);
            document.getElementById("edit_address_description").value = data.address_description;
            document.getElementById("edit_address_title").value = data.address_title;
            document.getElementById("editAddressModal").style.display = "block";
        })
        .catch(error => console.error("Error fetching address:", error));
}

function closeEditAddressModal() {
    document.getElementById("editAddressModal").style.display = "none";
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

window.onclick = function(event) {
    const addModal = document.getElementById("addAddressModal");
    const editModal = document.getElementById("editAddressModal");
    if (event.target === addModal) {
        addModal.style.display = "none";
    } else if (event.target === editModal) {
        editModal.style.display = "none";
    }
}