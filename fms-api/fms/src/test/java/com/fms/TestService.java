package com.fms;

import com.fms.domain.User;
import com.fms.repository.FileRepository;
import com.fms.repository.UserRepository;
import com.fms.service.FileService;
import com.fms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.fms.TestUserData.*;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserService userService;
    private final FileService fileService;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    public void populate() throws IOException {
        User kenshin=userService.saveUser(User.builder()
                        .id(UUID.randomUUID())
                        .email(KENSHINHIMURA_EMAIL_COM)
                        .password(KENSHIN_HIMURA_PASS_123)
                        .username(KENSHIN)
                .build());
        MockMultipartFile kenshinFile
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        fileService.createNewFile(kenshin.getId(),kenshinFile);
    }
    public void clear() {
        List<User> userList=userRepository.findAll();
        userList.forEach(user->{
            java.io.File userDirectory=new java.io.File(fileService.getUserDirectoryPath(user.getId().toString()));
            userDirectory.delete();
                    });
        fileRepository.deleteAll();
    }
}
