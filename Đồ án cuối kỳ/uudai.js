const offers = [
  {
    id: 1,
    title: "Vinpearl Resort Nha Trang",
    location: "Nha Trang",
    description: "Nghỉ dưỡng cao cấp tại bãi biển đẹp nhất Việt Nam",
    originalPrice: 3000000,
    price: 1800000,
    rating: 4.8,
    discountPercent: 40,
    endDate: "2024-12-31",
    code: "BEACH40",
    image:
      "https://cdn.pixabay.com/photo/2017/11/02/18/58/hotel-2918083_1280.jpg",
  },
  {
    id: 2,
    title: "JW Marriott Hanoi",
    location: "Hà Nội",
    description: "Khách sạn 5 sao sang trọng tại trung tâm Hà Nội",
    originalPrice: 2500000,
    price: 1750000,
    rating: 4.9,
    discountPercent: 30,
    endDate: "2024-11-30",
    code: "LUXURY30",
    image:
      "https://cdn.pixabay.com/photo/2015/12/08/17/19/hotel-1081800_1280.jpg",
  },
  {
    id: 3,
    title: "Combo Phú Quốc 3N2Đ",
    location: "Phú Quốc",
    description: "Gói nghỉ dưỡng trọn gói bao gồm vé máy bay + khách sạn",
    originalPrice: 8000000,
    price: 4000000,
    rating: 4.7,
    discountPercent: 50,
    endDate: "2024-12-15",
    code: "COMBO50",
    image:
      "https://cdn.pixabay.com/photo/2017/02/01/11/39/sea-2028481_1280.jpg",
  },
];

function createOfferCard(offer) {
  return `
    <div class="col-md-4">
      <div class="card h-100 shadow-sm rounded-3 position-relative">
        <div
          class="badge-discount position-absolute top-0 start-0 m-3 px-2 py-1 rounded-2 text-white fw-semibold bg-danger"
          style="font-size: 0.85rem;"
        >
          %-${offer.discountPercent}
        </div>
        <img
          src="${offer.image}"
          class="card-img-top"
          alt="${offer.title}"
          style="height: 180px; object-fit: cover;"
        />
        <div class="card-body d-flex flex-column">
          <h5 class="card-title fw-bold">${offer.title}</h5>
          <p class="text-muted mb-1"><i class="bi bi-geo-alt-fill me-1"></i>${offer.location}</p>
          <p class="text-secondary small mb-2">${offer.description}</p>
          <div class="mt-auto">
            <div class="d-flex align-items-center gap-2 mb-2">
              <div class="star-rating text-warning fw-semibold">
                ${offer.rating} <i class="bi bi-star-fill"></i>
              </div>
            </div>
            <div
              class="d-flex justify-content-between align-items-center mb-3 small text-muted"
            >
              <del class="text-decoration-line-through">${offer.originalPrice.toLocaleString("vi-VN")} đ</del>
              <span>Đến ${offer.endDate}</span>
              <span class="badge bg-info text-dark">${offer.code}</span>
            </div>
            <button class="btn btn-primary w-100">Đặt ngay</button>
          </div>
        </div>
      </div>
    </div>
  `;
}

function renderOffers() {
  const container = document.getElementById("offersList");
  container.innerHTML = offers.map(createOfferCard).join("");
}

document.addEventListener("DOMContentLoaded", () => {
  renderOffers();
});
