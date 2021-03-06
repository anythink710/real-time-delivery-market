package com.ht.project.realtimedeliverymarket.member.model.entity;

import com.ht.project.realtimedeliverymarket.member.model.dto.MemberJoinDto;
import com.ht.project.realtimedeliverymarket.member.model.vo.Address;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBERS")
@NoArgsConstructor
@Getter
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id", nullable = false)
  private Long id;

  @Column(name = "account", nullable = false)
  private String account;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "city", column = @Column(name = "member_city", nullable = false)),
          @AttributeOverride(name = "street", column = @Column(name = "member_street", nullable = false)),
          @AttributeOverride(name = "detail", column = @Column(name = "member_detail", nullable = false)),
          @AttributeOverride(name = "zipcode", column = @Column(name = "member_zipcode", nullable = false))
  })
  private Address address;

  @CreationTimestamp
  @Column(name = "create_at", nullable = false, updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  @Column(name = "update_at", nullable = false)
  private LocalDateTime updateAt;

  @Builder
  public Member(String account, String password, String name, String email, String phone, Address address) {
    this.account = account;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
  }

  public static Member from(MemberJoinDto memberJoinDto) {

    return Member.builder()
            .account(memberJoinDto.getAccount())
            .password(memberJoinDto.getPassword())
            .name(memberJoinDto.getName())
            .email(memberJoinDto.getEmail())
            .phone(memberJoinDto.getPhone())
            .address(new Address(memberJoinDto.getCity(),
                    memberJoinDto.getStreet(),
                    memberJoinDto.getDetail(),
                    memberJoinDto.getZipcode()))
            .build();
  }
}
