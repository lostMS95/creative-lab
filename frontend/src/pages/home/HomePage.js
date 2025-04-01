import React, { useState } from 'react';
import { Button, Input } from 'antd';

function HomePage() {
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
        <div></div>
        // <div className="upload-container">
        //     <h1>파일 업로드</h1>

        //     {/* 로그인된 사용자 이름 표시 */}
        //     {userInfo && (
        //         <p className="user-info">
        //             로그인된 사용자: {userInfo}
        //         </p>
        //     )}

        //     <form onSubmit={handleFormSubmit} encType="multipart/form-data">
        //         <div className="form-group">
        //             <label htmlFor="title">업로드 파일명:</label>
        //             <input type="text" className="form-control" id="title" name="title" required />
        //         </div>
        //         <div className="form-group">
        //             <label htmlFor="description">커밋메세지:</label>
        //             <textarea className="form-control" id="description" name="description" rows="2"></textarea>
        //         </div>

        //         {/* 셀렉트 박스들 */}
        //         <div className="form-group">
        //             <label htmlFor="position">소속:</label>
        //             <select className="form-control" id="position" name="position" required>
        //                 <option value="경영기획">경영기획</option>
        //                 <option value="사업기획">사업기획</option>
        //                 <option value="개발">개발</option>
        //                 <option value="회사공통">회사공통</option>
        //                 <option value="산업안전협회">산업안전협회</option>
        //                 <option value="경운대학교">경운대학교</option>
        //                 <option value="숭실대학교">숭실대학교</option>
        //                 <option value="밸류마크">밸류마크</option>
        //                 <option value="KMA">KMA</option>
        //                 <option value="선행기술">선행기술</option>
        //                 <option value="비즈비">비즈비</option>
        //             </select>
        //         </div>

        //         <div className="form-group">
        //             <label htmlFor="expend_type">지출구분:</label>
        //             <select className="form-control" id="expend_type" name="expend_type" required>
        //                 <option value="회사공통">회사공통</option>
        //                 <option value="야근식대">야근식대</option>
        //                 <option value="교통비_야근">교통비_야근</option>
        //                 <option value="친목활동">친목활동</option>
        //                 <option value="교통비_출장/파견">교통비_출장/파견</option>
        //                 <option value="교육비">교육비</option>
        //             </select>
        //         </div>

        //         <div className="form-group">
        //             <label htmlFor="card_type">카드 종류:</label>
        //             <select className="form-control" id="card_type" name="card_type" required>
        //                 <option value="개인카드">개인카드</option>
        //                 <option value="법인카드">법인카드</option>
        //             </select>
        //         </div>

        //         <div className="form-group">
        //             <label htmlFor="excel_date">날짜:</label>
        //             <input type="date" className="form-control" id="excel_date" name="excel_date" required />
        //         </div>

        //         <div className="form-group">
        //             <label htmlFor="excel_detail">내용:</label>
        //             <textarea className="form-control" id="excel_detail" name="excel_detail" rows="2" required></textarea>
        //         </div>

        //         <div className="form-group">
        //             <label htmlFor="excel_amount">금액:</label>
        //             <input type="number" className="form-control" id="excel_amount" name="excel_amount" required />
        //         </div>

        //         <div className="form-group">
        //             <label htmlFor="file">파일 선택:</label>
        //             <input type="file" className="form-control" id="file" name="file" required />
        //         </div>

        //         <button type="submit" className="btn btn-primary">업로드</button>
        //     </form>

        //     {loading && (
        //         <div className="loading-bar">
        //             <div></div><div></div><div></div>
        //         </div>
        //     )}

        //     {/* 업로드 후 메시지 */}
        //     {message && (
        //         <div className="alert alert-info">{message}</div>
        //     )}
        // </div>
    );
}

export default HomePage;
