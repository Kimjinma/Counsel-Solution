function submitSurvey() {
    const option1 = document.querySelector('input[name="option1"]:checked');
    const option2 = document.querySelector('input[name="option2"]:checked');
    const option3 = document.querySelector('input[name="option3"]:checked');

    if (!option1 || !option2 || !option3) {
        alert('모든 항목을 선택해주세요.');
        return;
    }

    const surveyResults = [
        {
            questionItemId: 1,
            questionTypeCode: "01",
            answerId: parseInt(option1.value)
        },
        {
            questionItemId: 2,
            questionTypeCode: "01",
            answerId: parseInt(option2.value)
        },
        {
            questionItemId: 3,
            questionTypeCode: "01",
            answerId: parseInt(option3.value)
        }
    ];

    fetch('/api/survey/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(surveyResults)
    })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
            if (data.message.includes('성공')) {
                document.getElementById('alertMessage').style.display = 'block';
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