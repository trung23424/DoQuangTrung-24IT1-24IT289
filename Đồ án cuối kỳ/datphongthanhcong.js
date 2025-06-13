document.addEventListener('DOMContentLoaded', () => {
  const bookingInfo = JSON.parse(localStorage.getItem('bookingInfo'));
  if (!bookingInfo) {
    alert('Không tìm thấy thông tin đặt phòng.');
    return;
  }

  // Tạo mã đặt phòng giả (bạn có thể thay bằng logic khác)
  const bookingCode = 'HB-2024-' + Math.floor(Math.random() * 900000 + 100000);
  document.getElementById('bookingCode').value = bookingCode;

  // Hiển thị thông tin khách sạn
  document.getElementById('hotelInfo').innerHTML = `
    <strong>${bookingInfo.hotelName}</strong><br/>
    ${bookingInfo.roomType}<br/>
    ${bookingInfo.location}
  `;

  // Hiển thị thời gian lưu trú
  document.getElementById('stayInfo').innerHTML = `
    Nhận phòng: ${bookingInfo.checkIn}<br/>
    Trả phòng: ${bookingInfo.checkOut}<br/>
    ${bookingInfo.nights} đêm
  `;

  // Thông tin khách
  document.getElementById('guestInfo').textContent = bookingInfo.guests;

  // Tổng chi phí
  const totalCost = bookingInfo.price + bookingInfo.tax;
  document.getElementById('totalCost').textContent = totalCost.toLocaleString('vi-VN') + ' ₫';
});
