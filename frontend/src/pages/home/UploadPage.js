import React, { useState } from 'react';
import { UploadOutlined } from '@ant-design/icons';
import { Button, Input, InputNumber, Select, Mentions, DatePicker, Upload, message } from 'antd';

const onChange = (date, dateString) => {
    console.log(date, dateString);
  };
  const props = {
    name: 'file',
    action: 'https://660d2bd96ddfa2943b33731c.mockapi.io/api/upload',
    headers: {
      authorization: 'authorization-text',
    },
    onChange(info) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === 'done') {
        message.success(`${info.file.name} file uploaded successfully`);
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} file upload failed.`);
      }
    },
  };

function UploadPg() {
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);
    const [userInfo, setUserInfo] = useState('');  // 로그인된 사용자 정보 저장

    const handleFormSubmit = (e) => {
        e.preventDefault();

        // 업로드 확인 메시지
        const confirmUpload = window.confirm("파일을 업로드하시겠습니까?");
        if (!confirmUpload) {
            return;
        }

        // 로딩 바 표시
        setLoading(true);

        // 예시: 서버에 데이터를 보내고 응답 받는 부분 처리
        setTimeout(() => {
            setLoading(false);
            setMessage("업로드 성공!");
        }, 3000); // 3초 후 메시지 변경
    };

    return (
        <div className="sub-container upload-container">
            {/* <h1>파일 업로드</h1> */}

            {/* 로그인된 사용자 이름 표시 */}
            {userInfo && (
                <p className="user-info">
                    로그인된 사용자: {userInfo}
                </p>
            )}

            <form onSubmit={handleFormSubmit} encType="multipart/form-data">
                <div className="form-group">
                    <label htmlFor="title">업로드 파일명</label>
                    <Input type="text" className="form-control" id="title" name="title" placeholder="파일명을 입력해 주세요." required />
                </div>
                <div className="form-group">
                    <label htmlFor="description">커밋메세지</label>
                    <Mentions className="form-control" id="description" name="description" rows={3} placeholder="커밋메세지를 남겨주세요."/>
                </div>

                {/* 셀렉트 박스들 */}
                <div className="form-group">
                    <label htmlFor="position">소속</label>
                    <Select style={{ width: '100%' }} className="form-control" id="position" name="position" required>
                        <option value="경영기획">경영기획</option>
                        <option value="사업기획">사업기획</option>
                        <option value="개발">개발</option>
                        <option value="회사공통">회사공통</option>
                        <option value="산업안전협회">산업안전협회</option>
                        <option value="경운대학교">경운대학교</option>
                        <option value="숭실대학교">숭실대학교</option>
                        <option value="밸류마크">밸류마크</option>
                        <option value="KMA">KMA</option>
                        <option value="선행기술">선행기술</option>
                        <option value="비즈비">비즈비</option>
                    </Select>
                </div>

                <div className="form-group">
                    <label htmlFor="expend_type">지출구분</label>
                    <Select style={{ width: '100%' }} className="form-control" id="expend_type" name="expend_type" required>
                        <option value="회사공통">회사공통</option>
                        <option value="야근식대">야근식대</option>
                        <option value="교통비_야근">교통비_야근</option>
                        <option value="친목활동">친목활동</option>
                        <option value="교통비_출장/파견">교통비_출장/파견</option>
                        <option value="교육비">교육비</option>
                    </Select>
                </div>

                <div className="form-group">
                    <label htmlFor="card_type">카드 종류</label>
                    <Select style={{ width: '100%' }} className="form-control" id="card_type" name="card_type" required>
                        <option value="개인카드">개인카드</option>
                        <option value="법인카드">법인카드</option>
                    </Select>
                </div>

                <div className="form-group">
                    <label htmlFor="excel_date">날짜</label>
                    <DatePicker onChange={onChange} />
                </div>

                <div className="form-group">
                    <label htmlFor="excel_detail">내용</label>
                    <Mentions className="form-control" id="excel_detail" name="excel_detail" rows={3} placeholder="내용을 입력해 주세요." required/>
                </div>

                <div className="form-group">
                    <label htmlFor="excel_amount">금액</label>
                    <InputNumber 
                        style={{ width: '100%', textAlign: 'right' }}
                        className="form-control" 
                        id="excel_amount" 
                        name="excel_amount"
                        formatter={(value) => `${value}원`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
                        placeholder="금액을 입력해 주세요." 
                        required 
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="file">파일 선택</label>
                    {/* <Input type="file" className="form-control" id="file" name="file" required /> */}
                    <Upload {...props}>
                        <Button icon={<UploadOutlined />}>Click to Upload</Button>
                    </Upload>
                </div>

                {/* <Button type="submit" className="btn btn-primary">업로드</Button> */}
            </form>
            
            {loading && (
                <div className="loading-bar">
                    <div></div><div></div><div></div>
                </div>
            )}

            {/* 업로드 후 메시지 */}
            {message && (
                <div className="alert alert-info">{message}</div>
            )}
        </div>
    );
}

export default UploadPg;
