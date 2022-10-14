# Place Order Outline
## Basic flows
1.	Khách hàng ấn vào xem giỏ hàng
2.	Hệ thống hiển thị các thông tin trong giỏ hàng
3.	Khách hàng nhấn yêu cầu đặt hàng
4.	Hệ thống yêu cầu khách hàng cập nhật thông tin giao hàng và chỉ dẫn giao hàng
5.	Hệ thống xác nhận thông tin đầu vào 
6.	Hệ thống tính phí giao hàng
7.	Hệ thống hiển thị và lưu lại thông tin đơn hàng tạm thời
8.	Khách hàng nhấn thanh toán đơn hàng, gọi đến use case thanh toán
9.	Hệ thống ghi lại thông tin giao dịch và đơn hàng 
10.	Hệ thống gửi thông tin giao dịch và thông tin đơn hàng đến hòm thư điện tử của khách hàng
## Alternative flows
1. Khách hàng loại bỏ sản phẩm khỏi giỏ hàng
2. Hệ thống thông báo số lượng hàng tồn kho không đủ và hiện thị ra số lượng hiện tại của sản phẩm
3. Khách hàng cập nhật lại giỏ hàng và yêu cầu đặt hàng lại
4. Khách hàng chọn phương thức giao hàng nhanh, gọi đến use case giao hàng nhanh
5. Hệ thống yêu cầu khách hàng cập nhật lại thông tin khi có trường bắt buộc bị bỏ trống hoặc thông tin không hợp lệ

