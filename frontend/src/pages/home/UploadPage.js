import React, { useState } from 'react';
import { UploadOutlined } from '@ant-design/icons';
import { Button, Input, InputNumber, Select, Mentions, DatePicker, Upload, message } from 'antd';

const { Option } = Select;

function UploadPg() {
    const [formData, setFormData] = useState({
        title: '',
        commitMessage: '',
        expPosition: '',
        expTp: '',
        expDt: null,
        expNm: '',
        expContents: '',
        expPymTp: '',
        expAmt: 0,
    });

    const [file, setFile] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleChange = (name, value) => {
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleDateChange = (date, dateString) => {
        setFormData((prev) => ({ ...prev, expDt: dateString }));
    };

    const handleFileChange = ({ file }) => {
        setFile(file.originFileObj);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!file) {
            message.error('파일을 선택해주세요.');
            return;
        }

        const confirmUpload = window.confirm("파일을 업로드하시겠습니까?");
        if (!confirmUpload) return;

        setLoading(true);
        const form = new FormData();
        Object.keys(formData).forEach((key) => {
            if (formData[key] !== null) form.append(key, formData[key]);
        });
        form.append("file", file);

        try {
            const response = await fetch("/svn/upload", {
                method: "POST",
                body: form,
            });

            if (!response.ok) throw new Error("업로드 실패");

            message.success("업로드 성공!");
        } catch (error) {
            message.error(error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="sub-container upload-container">
            <form onSubmit={handleSubmit} encType="multipart/form-data">
                <div className="form-group">
                    <label>제목</label>
                    <Input
                        name="title"
                        placeholder="예: 0101_홍길동_팀비사용"
                        required
                        onChange={(e) => handleChange("title", e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label>커밋 메시지</label>
                    <Mentions
                        rows={3}
                        placeholder="예: 엑셀 파일 업로드"
                        onChange={(value) => handleChange("commitMessage", value)}
                    />
                </div>
                <div className="form-group">
                    <label>소속</label>
                    <Select onChange={(value) => handleChange("expPosition", value)} required>
                        <Option value="개발">개발</Option>
                        <Option value="경영">경영</Option>
                    </Select>
                </div>
                <div className="form-group">
                    <label>지출 구분</label>
                    <Select onChange={(value) => handleChange("expTp", value)} required>
                        <Option value="팀비">팀비</Option>
                        <Option value="개인">개인</Option>
                    </Select>
                </div>
                <div className="form-group">
                    <label>일자</label>
                    <DatePicker onChange={handleDateChange} />
                </div>
                <div className="form-group">
                    <label>이름</label>
                    <Input
                        name="expNm"
                        placeholder="예: 홍길동"
                        required
                        onChange={(e) => handleChange("expNm", e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label>내용</label>
                    <Mentions
                        rows={3}
                        placeholder="예: 팀커피"
                        required
                        onChange={(value) => handleChange("expContents", value)}
                    />
                </div>
                <div className="form-group">
                    <label>결재 구분</label>
                    <Select onChange={(value) => handleChange("expPymTp", value)} required>
                        <Option value="승인">승인</Option>
                        <Option value="대기">대기</Option>
                    </Select>
                </div>
                <div className="form-group">
                    <label>금액</label>
                    <InputNumber
                        style={{ width: "100%" }}
                        formatter={(value) => `${value}원`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
                        onChange={(value) => handleChange("expAmt", value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>파일 선택</label>
                    <Upload beforeUpload={() => false} onChange={handleFileChange}>
                        <Button icon={<UploadOutlined />}>파일 업로드</Button>
                    </Upload>
                </div>
                <Button type="primary" htmlType="submit" loading={loading}>
                    업로드
                </Button>
            </form>
        </div>
    );
}

export default UploadPg;
