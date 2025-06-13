document.addEventListener('DOMContentLoaded', () => {
    const bookingInfo = JSON.parse(localStorage.getItem('bookingInfo'));
    if (!bookingInfo) {
        alert('Không tìm thấy thông tin đặt phòng.');
        return;
    }

    // Cập nhật thông tin chi tiết
    document.getElementById('hotelName').textContent = bookingInfo.hotelName;
    document.getElementById('roomInfo').innerHTML = `${bookingInfo.roomType}<br>${bookingInfo.location}`;

    // Hiển thị ngày, số đêm, số khách (định dạng đẹp)
    document.getElementById('checkInDD').textContent = bookingInfo.checkIn.split("-").reverse().join("/");
    document.getElementById('checkOutDD').textContent = bookingInfo.checkOut.split("-").reverse().join("/");
    document.getElementById('nightsDD').textContent = `${bookingInfo.nights} đêm`;
    document.getElementById('guestsDD').textContent = bookingInfo.guests;

    // Tính toán & hiển thị giá
    const subtotal = bookingInfo.subtotal;
    const tax = bookingInfo.tax;
    const totalCost = subtotal + tax;

    document.getElementById('price').textContent = subtotal.toLocaleString('vi-VN') + ' ₫';
    document.getElementById('tax').textContent = tax.toLocaleString('vi-VN') + ' ₫';
    document.getElementById('totalPrice').textContent = totalCost.toLocaleString('vi-VN') + ' ₫';
});

// Gửi form đặt phòng
document.getElementById('bookingForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const customerInfo = {
        lastName: document.getElementById('lastname').value,
        firstName: document.getElementById('firstname').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value,
        specialRequest: document.getElementById('specialRequest').value
    };

    console.log("Thông tin khách hàng:", customerInfo);

    // Chuyển trang sau khi đặt phòng
    window.location.href = 'datphongthanhcong.html';
});
