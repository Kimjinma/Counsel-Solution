function submitSurvey() {
    const option1 = document.querySelector('input[name="option1"]:checked');
    const option2 = document.querySelector('input[name="option2"]:checked');
    const option3 = document.querySelector('input[name="option3"]:checked');
    const subjectiveAnswer = document.querySelector('textarea[name="subjectiveAnswer"]');

    // 객관식 질문 검증
    if (!option1 || !option2 || !option3) {
        alert('모든 객관식 항목을 선택해주세요.');
        return;
    }

    // 주관식 질문 검증 (주관식 질문이 있을 경우에만)
    if (subjectiveAnswer && !subjectiveAnswer.value.trim()) {
        alert('주관식 답변을 입력해주세요.');
        return;
    }

    // 설문조사 결과 데이터 생성
    const surveyResults = [
        {
            questionItemId: 1,
            questionTypeCode: "MUL", // 객관식 코드를 "MUL"로 변경
            answerId: parseInt(option1.value),
            studentNo: "현재 로그인된 학생 번호"
        },
        {
            questionItemId: 2,
            questionTypeCode: "MUL",
            answerId: parseInt(option2.value),
            studentNo: "현재 로그인된 학생 번호"
        },
        {
            questionItemId: 3,
            questionTypeCode: "MUL",
            answerId: parseInt(option3.value),
            studentNo: "현재 로그인된 학생 번호"
        }
    ];

    // 주관식 답변 추가 (주관식 질문이 있을 경우에만)
    if (subjectiveAnswer && subjectiveAnswer.value.trim()) {
        surveyResults.push({
            questionItemId: 4,
            questionTypeCode: "SUB", // 주관식 코드를 "SUB"로 변경
            subjectiveAnswer: subjectiveAnswer.value.trim(),
            answerId: null  // 임시 answerId (백엔드에서 생성됨)
        });
    }

    // 설문조사 결과 서버로 전송
    fetch('/api/survey/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(surveyResults)
    })
        .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                throw new Error(data.message || '서버 오류가 발생했습니다.');
            }
            return data;
        })
        .then(data => {
            alert(data.message);
            if (data.message.includes('성공')) {
                const alertElement = document.getElementById('alertMessage');
                if (alertElement) {
                    alertElement.style.display = 'block';
                }
                // 성공 시 폼 초기화 또는 다음 페이지로 이동
                // window.location.href = '/success-page';
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('오류가 발생했습니다: ' + error.message);
        });
}

// 페이지 로드 시 이벤트 리스너 등록
document.addEventListener('DOMContentLoaded', function() {
    const submitButton = document.querySelector('.apply');
    if (submitButton) {
        submitButton.onclick = submitSurvey;
    }
});