function submitProgramApplication() {
    // 예시: 학생 정보와 선택한 프로그램 ID를 전송
    const studentNo = 'S123456'; // 학생 번호 (실제 데이터로 대체 필요)
    const programNo = document.getElementById('program').value; // 선택한 프로그램 번호 (선택한 값 대입)

    fetch('/api/program/apply', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ studentNo, programNo }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('예약이 완료되었습니다.');
                window.location.href = '/program/confirmation'; // 예약 확인 페이지로 리다이렉트
            } else {
                alert('오류가 발생했습니다. 다시 시도해주세요.');
            }
        })
        .catch(error => console.error('Error:', error));
}
