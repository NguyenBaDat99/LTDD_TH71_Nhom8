# LEnglish
## Mô tả project:
  - LEnglish là một ứng dụng học tập thông qua việc giúp người dùng cải thiện kĩ năng ngôn ngữ của mình, song với giao diện thân thiện, gần gũi tạo ra nhiều trải nghiệm mới mẻ, ứng dụng gồm 3 chức năng chính: Tra cứu, Luyện đọc, Luyện Nghe.
  
  - Xây dựng cở sở dữ liệu và ứng dụng phục vụ cho việc học tiếng anh gồm kỹ năng nghe và đọc. Người dùng có thể vào ứng dụng để tra cứu, luyện đọc, luyện nghe giúp nâng cao kĩ năng tiếng anh. Quản trị viên có thể vào FireBase để thêm các bài đọc cho ứng dụng của mình.

  - Dữ liệu được hỗ trợ bởi:
    + API Oxford Dictionary: https://developer.oxforddictionaries.com/ 
    + API Tra Câu: https://tracau.vn/pages/api.html 
    + FireBase 

## Danh sách thành viên:
+ 1751010022 - Nguyễn Bá Đạt
+ 1751010026 - Đoàn Quí Đông
+ 1751010054 - Nguyễn Xuân Hưng
+ 1751010090 - Võ Văn Nhật Minh
+ 1751010083 - Đặng Quang Minh

## Phân công công việc:
 - Tạo cơ sở dữ liệu FireBase: Nhật Minh
 - Tạo Menu Setting:
   + Menu bottom navigation (gồm 3 item Tra cứu, Luyện đọc, Luyện nghe):
   + Menu hiển thị trên thanh Toolbar (gồm 2 item Cài đặt, Giới thiệu):
 ### Giao diện chính gồm 3 Fragment:
  #### Fragment Tra cứu 
  - Thiết kế giao diện: 
  - Xử lý chức năng tìm kiếm gồm 3 chế độ:
    + Anh - Việt (lấy API từ Tra câu):
    + Anh - Anh (lấy API từ Oxford):
    + Việt - Anh (lấy API từ Tra câu):
  - Phiên âm, audio từ tìm kiếm (hiển thị ở Anh - Việt, Anh - Anh lấy API từ Oxford):
  #### Fragment Luyện đọc 
  - Thiết kế giao diện:
  - Xử lý chức năng tìm kiếm theo tiêu đề mỗi bài đọc:
  - Tạo danh sách bài đọc (lấy dữ liệu từ FireBase):
  - Xử lý chức năng hiển thị chi tiết bài đọc được chọn:
  #### Fragment Luyện nghe 
  - Thiết kế giao diện: 
  - Xử lý chức năng tìm kiếm theo (từng từ, địa chỉ đường dẫn):
  - Hiển thị kết quả tìm kiếm:
    + Từng từ (Xuất ra những video liên quan đến từ cần tìm):
    + Địa chỉ đường dẫn (Dẫn tới địa chỉ theo đúng đường dẫn):
 ### Giao diện phụ gồm 2 Activity:
  + Activity Cài đặt (Giao diện, xử lý chế độ DarkMode):
  + Activity Giới thiệu (Giao diện):
## Hướng dẫn sử dụng:
 ### Phía người dùng:
   ● Tra cứu: Dịch(giải thích) những từ không hiểu hoặc muốn biết thêm nghĩa của từ do người dùng nhập vào, với 3 chế độ dịch khác nhau: Anh - Việt, Anh - Anh, Việt - Anh.
        
   ● Luyện đọc: Bao gồm nhiều bài viết với nhiều cấp độ IELTS, Mover, Starter, TOEIC cho người dùng lựa chọn. Mỗi đọc sẽ giúp người dùng cải thiện kĩ năng đọc hiểu song với từ ngữ từ dễ đến khó tương ứng với cấp dộ người dùng chọn.
   
   ● Luyện nghe: Gần giống với chức năng Tra cứu đều cho người dùng nhập từ khóa nhưng kết quả là hiện ra những video, giúp người dùng nghe và thấy được cách phát âm của từ họ cần tìm kiếm. Ngoài ra Luyện nghe còn có 3 trang web gợi ý TED, BBC Learning, Youtube giúp cải thiện kĩ năng nghe cho người dùng.
   
  ● Ngoài ra ứng dụng LEnglish còn 2 giao diện Cài đặt và Giới thiêu:
   + Cài đặt: Người dùng có thể đổi chế độ Light Mode sang Dark Mode.
   + Giới thiệu: Ở đây người dùng có thể xem chi tiết về ứng dụng cũng như thông tin của chúng tôi.
 
 
