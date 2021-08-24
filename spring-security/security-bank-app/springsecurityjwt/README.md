- Để check secured spring security ta có 2 cách
    1: + implements UserDetail ở entity mà ta muốn dùng để đăng nhập, như trong bài là Entity Customer trong class CustomerUserDetails
    + Sau đó trong sẽ tạo lớp implements interface UserDetailsSevice và overice lại hàm loadUserByUsername() rồi viết logic tìm trong database có user đăng nhập đó không.
    + Trong Config:
        // config UserDetailsService
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsServiceImpl); 
        }
    
    2: + Sử dụng AuthenticationProvider
    + Xem file SecurityConfig sử dụng DaoAuthenticationProvider và set lớp UserDetailsServiceImpl vào DaoAuthenticationProvider để nó gọi tới hàm loadUserByUsername() để hàm đó check user đăng nhập
    + Sau đó dùng config:
        /*
        * config AuthenticationProvider
        */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider);
        }

- Tiếp đó tạo các phương thức cho JWT tham khảo: https://www.javainuse.com/spring/boot-jwt

- Rồi viêt 1 restapi login sử dụng:
    authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword(), Authorities));
    --> để check user đăng nhập nếu pass đăng nhập sẽ generate token và trả về

- Sau đó sẽ viết filter security để check token khi client gọi api lấy dữ liệu --> xem lớp JwtRequestFilter --> cấu hình configure() http filter để nó check token
		
