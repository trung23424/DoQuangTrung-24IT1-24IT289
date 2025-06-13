// Dữ liệu mô phỏng danh sách khách sạn
const hotels = [
    {
        id: 1,
        name: "Khách sạn Grand Luxury",
        location: "Quận 1, Hồ Chí Minh",
        price: 1500000,
        rating: 4.8,
        ratingCount: 219,
        features: ["WiFi miễn phí", "Hồ bơi", "Gym", "Spa", "Sân tennis"],
        discount: 30,
        image: "https://pix10.agoda.net/hotelImages/10974/-1/1852cfb66add1ee8ba1f43e2a1fd4380.jpg?ce=0&s=414x232&ar=16x9"
    },
    {
        id: 2,
        name: "Resort Paradise Beach",
        location: "Phú Quốc, Kiên Giang",
        price: 2800000,
        rating: 4.9,
        ratingCount: 2127,
        features: ["Bãi biển riêng", "All-inclusive", "WiFi miễn phí", "Spa", "Trẻ em"],
        discount: 0,
        image: "https://phuquocxanh.com/vi/wp-content/uploads/2015/11/Paradise-Resort1.jpg"
    },
    {
        id: 3,
        name: "Hotel Central Business",
        location: "Ba Đình, Hà Nội",
        price: 1200000,
        rating: 4.6,
        ratingCount: 1123,
        features: ["WiFi miễn phí", "Phòng họp", "Gym", "Nhà hàng"],
        discount: 30,
        image: "https://image.vietgoing.com/hotel/02/68/vietgoing_adm2311178225.webp"
    },
    {
        id: 4,
        name: "Mountain View Hotel",
        location: "Sa Pa, Lào Cai",
        price: 800000,
        rating: 4.7,
        ratingCount: 367,
        features: ["View núi", "WiFi miễn phí", "Nhà hàng", "Tour guide"],
        discount: 0,
        image: "https://cf.bstatic.com/xdata/images/hotel/max1024x768/226356631.jpg?k=fbb66184d3d23583aed7cd9bdab4b6d407a138ef55e48a7d7538c3229bb1435e&o=&hp=1"
    },
    {
        id: 5,
        name: "Boutique Hotel Đà Nẵng",
        location: "Hải Châu, Đà Nẵng",
        price: 1800000,
        rating: 4.5,
        ratingCount: 1452,
        features: ["WiFi miễn phí", "Hồ bơi", "Spa", "Gần biển"],
        discount: 0,
        image: "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/ee/cb/3b/danang-boutique-hotel.jpg?w=900&h=500&s=1"
    },
    {
        id: 6,
        name: "Heritage Hotel Hội An",
        location: "Phố cổ, Hội An",
        price: 1400000,
        rating: 4.7,
        ratingCount: 922,
        features: ["WiFi miễn phí", "Xô đập", "Spa", "Nhà hàng"],
        discount: 20,
        image: "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/05/62/57/8f/van-loi-hotel.jpg?w=900&h=500&s=1"
    }
];

function formatPrice(price) {
    return price.toLocaleString("vi-VN", { style: "currency", currency: "VND" });
}

function createHotelCard(hotel) { 
    const features = hotel.features.map(f => `<span class="badge bg-secondary me-2 mb-1">${f}</span>`).join(""); 
    const discountBadge = hotel.discount > 0 ? `<div class="badge-discount">Giảm ${hotel.discount}%</div>` : ""; 

    // Tính giá gốc nếu có giảm 
    const originalPrice = hotel.discount > 0 ? `<del class="text-muted">${hotel.originalPrice?.toLocaleString("vi-VN")} đ</del>` : ""; 

    return ` 
    <div class="col-12"> 
      <div class="card hotel-card mb-4 shadow-sm rounded-3"> 
        <div class="row g-0 align-items-center"> 
          <div class="col-md-5 position-relative"> 
            ${discountBadge} 
            <img src="${hotel.image}" alt="${hotel.name}" class="img-fluid rounded-start" style="height: 220px; object-fit: cover; width: 100%;" /> 
          </div> 
          <div class="col-md-7"> 
            <div class="card-body"> 
              <h5 class="card-title fw-bold">${hotel.name}</h5> 
              <p class="mb-1 text-muted"><i class="bi bi-geo-alt-fill me-1"></i>${hotel.location}</p> 
              <div class="mb-2 d-flex align-items-center"> 
                <span class="star-rating text-warning fw-semibold me-2">${hotel.rating.toFixed(1)} <i class="bi bi-star-fill"></i></span> 
                <small class="text-muted">(${hotel.ratingCount})</small> 
              </div> 
              <div class="mb-2 hotel-features d-flex flex-wrap gap-2">${features}</div> 
              <div class="mb-3 fs-5 fw-semibold text-primary"> 
                ${originalPrice ? originalPrice + ' ' : ''} 
                ${hotel.price.toLocaleString("vi-VN")} đ <small class="text-muted">/đêm</small> 
              </div> 
           <a href="#" class="btn btn-orange" onclick="goToDetail(${hotel.id})">Xem chi tiết</a>
            </div> 
          </div> 
        </div> 
      </div> 
    </div> 
    `; 
}

function renderHotels() {
    const container = document.getElementById("hotelList");
    container.innerHTML = hotels.map(createHotelCard).join("");
}

document.addEventListener("DOMContentLoaded", () => {
    renderHotels();

    const priceSlider = document.getElementById("priceRange");
    const priceMin = document.getElementById("priceMin");

    priceSlider.addEventListener("input", () => {
      priceMin.textContent = Number(priceSlider.value).toLocaleString("vi-VN") + " đ";
    });

    // Nếu muốn gán giá trị ban đầu:
    priceMin.textContent = Number(priceSlider.value).toLocaleString("vi-VN") + " đ";
});
// // ======= CHỨC NĂNG TÌM KIẾM KHÁCH SẠN THEO ĐIỂM ĐẾN =======
// document.querySelector(".search-box form").addEventListener("submit", function (e) {
//     e.preventDefault(); // Ngăn reload trang

//     const keyword = document.getElementById("destination").value.trim().toLowerCase();

//     // Lọc danh sách khách sạn theo tên hoặc địa chỉ chứa từ khóa
//     const filtered = hotels.filter(hotel => {
//         return hotel.name.toLowerCase().includes(keyword) ||
//                hotel.location.toLowerCase().includes(keyword);
//     });

//     const container = document.getElementById("hotelList");
//     if (filtered.length === 0) {
//         container.innerHTML = `<p class="text-danger">Không tìm thấy khách sạn phù hợp với "${keyword}"</p>`;
//     } else {
//         container.innerHTML = filtered.map(createHotelCard).join("");
//     }
// });

document.querySelector(".search-box form").addEventListener("submit", function (e) {
    e.preventDefault();

    const keyword = document.getElementById("destination").value.trim().toLowerCase();
    const checkin = document.getElementById("checkinDate").value;
    const checkout = document.getElementById("checkoutDate").value;
    const guestRoom = document.getElementById("guestRoom").value;

    const query = new URLSearchParams({
        checkin,
        checkout,
        guestRoom
    }).toString();

    // Chọn khách sạn phù hợp đầu tiên
    const firstMatch = hotels.find(h =>
        h.name.toLowerCase().includes(keyword) ||
        h.location.toLowerCase().includes(keyword)
    );

    const filtered = hotels.filter(h =>
    h.name.toLowerCase().includes(keyword) ||
    h.location.toLowerCase().includes(keyword)
);

const container = document.getElementById("hotelList");
if (filtered.length === 0) {
    container.innerHTML = `<p class="text-danger">Không tìm thấy khách sạn phù hợp với "${keyword}"</p>`;
} else {
    container.innerHTML = filtered.map(createHotelCard).join("");
}

});

// ======= ÁP DỤNG BỘ LỌC =======
document.getElementById("applyFilters").addEventListener("click", function () {
    const maxPrice = Number(document.getElementById("priceRange").value);

    // Đánh giá
    const selectedStars = [];
    for (let i = 1; i <= 5; i++) {
        if (document.getElementById("star" + i).checked) {
            selectedStars.push(i);
        }
    }

    // Tiện ích
    const selectedFeatures = [];
    const allFeatureCheckboxes = document.querySelectorAll(".filter-sidebar .form-check-input");
    allFeatureCheckboxes.forEach(cb => {
        const label = document.querySelector(`label[for='${cb.id}']`);
        if (cb.checked && label && !cb.id.startsWith("star")) {
            selectedFeatures.push(label.textContent.trim());
        }
    });

    // Lọc dữ liệu
    const filtered = hotels.filter(hotel => {
        const withinPrice = hotel.price <= maxPrice;

        const withinRating = selectedStars.length === 0 || selectedStars.some(star => hotel.rating >= star);

        const hasFeatures = selectedFeatures.length === 0 || selectedFeatures.every(f => hotel.features.includes(f));

        return withinPrice && withinRating && hasFeatures;
    });

    const container = document.getElementById("hotelList");
    if (filtered.length === 0) {
        container.innerHTML = `<p class="text-danger">Không tìm thấy khách sạn phù hợp với bộ lọc.</p>`;
    } else {
        container.innerHTML = filtered.map(createHotelCard).join("");
    }
});
// ======= XÓA BỘ LỌC =======
document.getElementById("clearFilters").addEventListener("click", () => {
    // Reset range giá
    document.getElementById("priceRange").value = 10000000;
    document.getElementById("priceMin").textContent = "10.000.000 đ";

    // Reset các checkbox
    document.querySelectorAll(".filter-sidebar .form-check-input").forEach(input => input.checked = false);

    // Hiển thị lại toàn bộ khách sạn
    renderHotels();
});
function goToDetail(hotelId) {
    const checkin = document.getElementById("checkinDate").value;
    const checkout = document.getElementById("checkoutDate").value;
    const guestRoom = document.getElementById("guestRoom").value;

    const query = new URLSearchParams({
        checkin,
        checkout,
        guestRoom
    }).toString();

    window.location.href = `xemkhachsan.html?id=${hotelId}&${query}`;
}


