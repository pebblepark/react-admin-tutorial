package com.example.demo.domain.file;

import com.example.demo.domain.common.AbstractAuditEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class File extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_id")
	private Long id;

	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "file_path", nullable = false)
	private String filePath;
	
	@Column(name = "file_size", nullable = false)
	private String fileSize;

	@Column(name = "file_type", nullable = false)
	private String fileType;
	
	@Column(name = "download_cnt", nullable = false)
	@ColumnDefault("0")
	private int downloadCnt;
}
